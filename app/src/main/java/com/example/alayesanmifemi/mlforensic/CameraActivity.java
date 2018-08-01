package com.example.alayesanmifemi.mlforensic;

//import android.content.Context;
//import android.graphics.ImageFormat;
//import android.graphics.SurfaceTexture;
//import android.hardware.camera2.CameraAccessException;
//import android.hardware.camera2.CameraCaptureSession;
//import android.hardware.camera2.CameraCharacteristics;
//import android.hardware.camera2.CameraDevice;
//import android.hardware.camera2.CameraManager;
//import android.hardware.camera2.CameraMetadata;
//import android.hardware.camera2.CaptureRequest;
//import android.hardware.camera2.TotalCaptureResult;
//import android.media.Image;
//import android.media.ImageReader;
//import android.os.Environment;
//import android.os.Handler;
//import android.os.HandlerThread;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Printer;
//import android.util.Size;
//import android.util.SparseIntArray;
//import android.view.Surface;
//import android.view.TextureView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.ByteBuffer;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.UUID;
//
//public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private Button btn_capture;
//    private Button btn_detect;
//    private Button btn_cancel;
//
//    private TextureView textureView;
//
//    public  static final SparseIntArray ORIENTATIONS = new SparseIntArray();
//    static {
//        ORIENTATIONS.append(Surface.ROTATION_0,90);
//        ORIENTATIONS.append(Surface.ROTATION_90,0);
//        ORIENTATIONS.append(Surface.ROTATION_180,270);
//        ORIENTATIONS.append(Surface.ROTATION_270,180);
//    }
//    private String CameraID;
//    private CameraDevice cameraDevice;
//    private CameraCaptureSession cameraCaptureSession;
//    private CaptureRequest.Builder captureRequestBuilder;
//    private Size imageDimension;
//    private ImageReader imageReader;
//
//
//    private File file;
//    private static final int REQUEST_CAMERA_PERMISSION = 200;
//    private boolean flashSupported;
//    private Handler mBackgroundHandler;
//    private HandlerThread mBackgroundHandlerThread;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_camera);
//        textureView = (TextureView) findViewById(R.id.texttureView);
//        assert textureView != null;
//        btn_detect = (Button) findViewById(R.id.btn_detect);
//        btn_capture = (Button) findViewById(R.id.btn_capture);
//        btn_cancel = (Button) findViewById(R.id.btn_cancel);
//
//
//        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
//            @Override
//            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
//
//            }
//
//            @Override
//            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
//
//            }
//
//            @Override
//            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
//                return false;
//            }
//
//            @Override
//            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
//
//            }
//
//        });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        switch(view.getId()){
//            case R.id.btn_detect:
//                takePicture();
//                break;
//        }
//    }
//
//    public void takePicture() {
//        if (cameraDevice == null) {
//            return;
//        }
//        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        try {
//            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
//            Size[] jpegsizes = null;
//            if (characteristics != null) {
//                jpegsizes - characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
//            }
//            int width = 640;
//            int height = 640;
//            if (jpegsizes != null && jpegsizes.length > 0) {
//                width = jpegsizes[0].getWidth();
//                height = jpegsizes[0].getHeight();
//            }
//            final ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
//            List<Surface> outputDSurface = new ArrayList<>(2);
//            outputDSurface.add(reader.getSurface());
//            outputDSurface.add(new Surface(textureView.getSurfaceTexture()));
//
//            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
//            captureBuilder.addTarget(reader.getSurface());
//            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
//
//            int rotation = getWindowManager().getDefaultDisplay().getRotation();
//            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
//
//            file = new File(Environment.getExternalStorageDirectory() + "/" + new UUID().randomUUID().toString() + ".jpg");
//            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
//                @Override
//                public void onImageAvailable(ImageReader imageReader) {
//                    Image image = null;
//                    try {
//                        image = reader.acquireLatestImage();
//                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
//                        byte[] bytes = new byte[buffer.capacity()];
//                        buffer.get(bytes);
//                        save(bytes);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    } finally {
//                        {
//                            if (image != null)
//                                image.close();
//                        }
//                    }
//
//                }
//                private void save(byte[] bytes) throws IOException{
//                    OutputStream outputStream = null;
//                    try{
//                        outputStream = new FileOutputStream(file);
//                        outputStream.write(bytes);
//                }finally{
//                        if(outputStream != null)
//                            outputStream.close();
//                    }
//                }
//
//             };
//           reader.setOnImageAvailableListener(readerListener, mBackgroundHandler);
//           final CameraCaptureSession.CaptureCallback captureListner = new CameraCaptureSession.CaptureCallback() {
//               @Override
//               public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
//                   super.onCaptureCompleted(session, request, result);
//                   Toast.makeText(CameraActivity.this, "Saved" + file, Toast.LENGTH_LONG).show();
//                   createCameraPreview();
//               }
//           };
//           cameraDevice.createCaptureSession(outputDSurface, new CameraCaptureSession.StateCallback() {
//               @Override
//               public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
//                   try{
//                       cameraCaptureSession.capture(captureBuilder.build(), captureListner, mBackgroundHandler);
//                   }catch (CameraAccessException e){
//                       e.printStackTrace();
//                   }
//
//               }
//
//               @Override
//               public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
//
//               }
//           } ,mBackgroundHandler );
//
//    }
//        catch (CameraAccessException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    private void createCameraPreview() {
//        try{
//            SurfaceTexture texture = textureView.getSurfaceTexture();
//            assert texture != null;
//            texture.setDefaultBufferSize(imageDimension.getWidth(), imageDimension.getHeight());
//            Surface surface = new Surface(texture);
//            captureRequestBuilder =  cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//            captureRequestBuilder.addTarget(surface);
//            cameraDevice.createCaptureSession(Arrays.asList(surface), new CameraCaptureSession.StateCallback() {
//                @Override
//                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
//                    if(cameraDevice == null){
//                        return;
//                        cameraCaptureSession = cameraCaptureSession;
//                        updatePreview();
//                    }
//
//                }
//
//                @Override
//                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {
//
//                }
//            }, null);
//
//
//        }catch (){
//
//        }
//    }
//
//    private void updatePreview() {
//    }
//
//    private void openCamera(){
//
//    }
//}
