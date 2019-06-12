package br.edu.iftm.pdm.car4sale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import br.edu.iftm.pdm.car4sale.model.Picture;

public class TakePicturesActivity extends AppCompatActivity {

    public static final String PICTURES_KEY = "br.edu.iftm.pdm.car4sale.takepictures.PICTURES";
    private static int REQUEST_CAMERA_CODE = 1234;
    private ArrayList<Picture> pictures;
    private ImageButton btnTakePicture;
    private File pictureFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_pictures);

        this.btnTakePicture = (ImageButton) findViewById(R.id.btnTakePicture);

        this.pictures = getIntent().getParcelableArrayListExtra(PICTURES_KEY);
        if(this.pictures == null)
            this.pictures = new ArrayList<>();
    }

    public String generateFileName() {
        // Cada imagem precisará de um nome especial e gerado automaticamente
        // use este método para isso
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return "TIS_"+timeStamp+".jpg";
    }

    public Uri getUri(){
        //Este método deveria retornar uma Uri com a informação do path da imagem
        //As imagens deverão ser dispostas por meio do armazenamento Environment.getExternalStorageDirectory()
        //Não se esqueça da configuração do FileProvider!
        File path = new File(Environment.getExternalStorageDirectory(), "car4sale/car_imgs");
        if(!path.exists() && !path.mkdirs()){
            Toast.makeText(this, "Failed to create dirs...", Toast.LENGTH_SHORT).show();
        }
        this.pictureFile = new File(path, this.generateFileName());
        return FileProvider.getUriForFile(this, "br.edu.iftm.pdm.car4sale", pictureFile);
    }

    public void onClickTakePicture(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            Uri uri = this.getUri();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, REQUEST_CAMERA_CODE);
        }
        else {
            Toast.makeText(this, getString(R.string.camera_not_found), Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSeeGallery(View view) {
        Intent intent = new Intent(this, ListPicturesActivity.class);
        intent.putExtra(ListPicturesActivity.LIST_PICTURE_KEY, this.pictures);
        startActivity(intent);
    }

    public void onClickDone(View view) {
        Intent output = new Intent();
        output.putExtra(PICTURES_KEY, this.pictures);
        this.setResult(RESULT_OK, output);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.setResult(RESULT_CANCELED);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK) {
            // Resgate a imagem, mostre no imageButton e salve no arrayList de pictures.
            String path = this.pictureFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            btnTakePicture.setImageBitmap(bitmap);

            Picture picture = new Picture();
            picture.setPath(path);
            this.pictures.add(picture);
        }
    }
}
