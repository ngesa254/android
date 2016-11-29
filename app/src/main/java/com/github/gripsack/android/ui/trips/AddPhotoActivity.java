package com.github.gripsack.android.ui.trips;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.github.gripsack.android.R;
import com.github.gripsack.android.utils.CapturePhoto;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPhotoActivity extends AppCompatActivity implements View.OnClickListener{

    public final static int REQUEST_IMAGE_CAPTURE = 1;
    public final static int REQEST_CODE=13;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
   /* @BindView(R.id.rvPhotos)
    RecyclerView rvPhotos;*/
    @BindView(R.id.btnAddPhoto)
    ImageButton btnAddPhoto;
    ArrayList<String> imageUrls;
    PhotosAdapter adapter;
    @BindView(R.id.preview)
    ImageView ivPreview;

    private CapturePhoto capture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        imageUrls=new ArrayList<String>();
        //TODO:Get urls from firebase
        adapter=new PhotosAdapter(this,imageUrls);
        //rvPhotos.setAdapter(adapter);

        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        //rvPhotos.setLayoutManager(gridLayoutManager);

        ivPreview.setOnClickListener(this);
        capture = new CapturePhoto(this);
        btnAddPhoto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int targetID = v.getId();
     if(R.id.btnAddPhoto==targetID)
         selectImage(REQEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if(capture.getActionCode() == CapturePhoto.PICK_IMAGE) {
                Uri targetUri = data.getData();
                if(targetUri != null)
                    capture.handleMediaPhoto(targetUri, ivPreview);
            }
            else {
                capture.handleCameraPhoto(ivPreview);
            }
        }
    }

    private void selectImage(final int id) {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(AddPhotoActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    // take photo from camera
                    capture.dispatchTakePictureIntent(CapturePhoto.SHOT_IMAGE, id);
                } else if (items[item].equals("Choose from Library")) {
                    // take photo from gallery
                    capture.dispatchTakePictureIntent(CapturePhoto.PICK_IMAGE, id);
                } else if (items[item].equals("Cancel")) {
                    // close the dialog
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
