package com.example.android.medistats;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraPreview extends AppCompatActivity implements SurfaceHolder.Callback
{
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    public static Uri savedImageURI;
    public static int a = 1;
    final int RESULT_SAVEIMAGE = 0;
    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    boolean previewing = false;
    LayoutInflater controlInflater = null;
    Button buttonTakePicture;
    DrawingView drawingView;
    ImageView image;
    ShutterCallback myShutterCallback = new ShutterCallback()
    {
        @Override
        public void onShutter()
        {
            // TODO Auto-generated method stub

        }
    };
    PictureCallback myPictureCallback_RAW = new PictureCallback()
    {
        @Override
        public void onPictureTaken(byte[] arg0, Camera arg1)
        {
            // TODO Auto-generated method stub

        }
    };
    PictureCallback myPictureCallback_JPG = new PictureCallback()
    {
        @Override
        public void onPictureTaken(byte[] data, Camera camera)
        {
            // TODO Auto-generated method stub
            if (data != null) {
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                int screenHeight = getResources().getDisplayMetrics().heightPixels;
                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, (data != null) ? data.length : 0);

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    // Notice that width and height are reversed
                    Bitmap scaled = Bitmap.createScaledBitmap(bm, screenHeight, screenWidth, true);
                    int w = scaled.getWidth();
                    int h = scaled.getHeight();
                    // Setting post rotate to 90
                    Matrix mtx = new Matrix();
                    mtx.postRotate(270);
                    // Rotating Bitmap
                    bm = Bitmap.createBitmap(scaled, 0, 0, w, h, mtx, true);
                    // image.setImageBitmap(bm);
                    ContextWrapper wrapper = new ContextWrapper(getApplicationContext());

                    File file = wrapper.getDir("Images", MODE_PRIVATE);

                    file = new File(file, "UniqueFileName" + ".jpg");

                    try
                    {
                        OutputStream stream = null;
                        stream = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        stream.flush();
                        stream.close();

                    }
                    catch (IOException e) // Catch the exception
                    {
                        e.printStackTrace();
                    }

                    // Parse the gallery image url to uri
                    savedImageURI = Uri.parse(file.getAbsolutePath());
                    Log.e("TAG", " " + savedImageURI);
                    //Intent intent = new Intent(MainActivity.this, saveimageActivity.class);
                    //startActivity(intent);
                }
            }

            camera.startPreview();
        }
    };
    private ScheduledExecutorService myScheduledExecutorService;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(CameraPreview.this, new String[]{Manifest.permission.CAMERA}, 50);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 50);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {

                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE))
                {
                }

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {

                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE))
                {
                }

                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }

        getWindow().setFormat(PixelFormat.UNKNOWN);
        surfaceView = (SurfaceView) findViewById(R.id.cam1);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        /*drawingView = new DrawingView(this);
        LinearLayout.LayoutParams layoutParamsDrawing
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        this.addContentView(drawingView, layoutParamsDrawing);

        controlInflater = LayoutInflater.from(getBaseContext());
        View viewControl = controlInflater.inflate(R.layout.control, null);
        LinearLayout.LayoutParams layoutParamsControl
                = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        this.addContentView(viewControl, layoutParamsControl);
//image=(ImageView) findViewById(R.id.imageView);
        LinearLayout l = (LinearLayout) findViewById(R.id.background);
        if (a == 1) {
            l.setBackgroundResource(R.drawable.transparent_side);
        }
        if (a == 2) {
            l.setBackgroundResource(R.drawable.transparent_side);
        }*/
        buttonTakePicture = (Button) findViewById(R.id.takepic);
        buttonTakePicture.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                camera.takePicture(myShutterCallback,
                        myPictureCallback_RAW, myPictureCallback_JPG);
            }
        });
    }

    private void storeImage(Bitmap image)
    {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null)
        {
            Log.d("TAG1",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try
        {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            Log.d("TAG",
                    "Error creating media file, check storage permissions: ");// e.getMessage());

        }
        catch (FileNotFoundException e)
        {
            Log.d("TAG", "File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            Log.d("TAG", "Error accessing file: " + e.getMessage());
        }
    }

    private File getOutputMediaFile()
    {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.DIRECTORY_DCIM
                + "/Android/data/"
                + getApplicationContext().getPackageName()
                + "/Files");
        Log.d("TAG",
                "Error creating media file, check storage permissions: ");// e.getMessage());

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists())
        {
            if (!mediaStorageDir.mkdirs())
            {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height)
    {
        // TODO Auto-generated method stub
        if (camera != null)
        {
            try
            {
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {
      /*  int cameraCount = 0;
        int camIdx;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        cameraCount = Camera.getNumberOfCameras();
        for ( camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    camera = Camera.open(camIdx);
                } catch (RuntimeException e) {
                    Log.e("TAG", "Camera failed to open: " + e.getLocalizedMessage());
                }
            }
            break;
        }*/
        int cameraId = findFrontFacingCamera();
        if (cameraId < 0)
        {
            Toast.makeText(this, "No  camera found.",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            camera = Camera.open(cameraId);
        }

        android.hardware.Camera.CameraInfo info =
                new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);
        int rotation = this.getWindowManager().getDefaultDisplay()
                .getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result = 0;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
        {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        }
        else
        {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }

        camera.setDisplayOrientation(result);
        try
        {
            camera.setPreviewDisplay(surfaceHolder);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        // TODO Auto-generated method stub
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    private int findFrontFacingCamera()
    {
        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++)
        {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT)
            {
                Log.d("tag:", "Camera found");
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }


    private class DrawingView extends View
    {
        boolean haveFace;
        Paint drawingPaint;
        public DrawingView(Context context)
        {
            super(context);
            haveFace = false;
            drawingPaint = new Paint();
            drawingPaint.setColor(Color.GREEN);
            drawingPaint.setStyle(Paint.Style.STROKE);
            drawingPaint.setStrokeWidth(2);
        }

        public void setHaveFace(boolean h)
        {
            haveFace = h;
        }

        @Override
        protected void onDraw(Canvas canvas)
        {
            // TODO Auto-generated method stub

            int vWidth = getWidth();
            int vHeight = getHeight();
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CameraPreview.this,SignUp.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        // open camera
        camera = Camera.open();

        // init surface view
        surfaceView = (SurfaceView)this.findViewById(R.id.cam1);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        surfaceHolder.setSizeFromLayout();
        surfaceHolder.addCallback(this);
    }
}
