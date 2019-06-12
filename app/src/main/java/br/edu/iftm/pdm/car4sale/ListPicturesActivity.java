package br.edu.iftm.pdm.car4sale;

import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.model.Picture;
import br.edu.iftm.pdm.car4sale.view.PictureAdapter;
import br.edu.iftm.pdm.car4sale.view.PictureDialogFragment;

public class ListPicturesActivity extends AppCompatActivity implements PictureAdapter.PictureListener {

    public static final String LIST_PICTURE_KEY = "br.edu.iftm.pdm.car4sale.listpictures.PICTURE";

    private RecyclerView rvPictures;
    private PictureAdapter pictureAdapter;
    private ArrayList<Picture> pictures;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pictures);
        // Aqui nesta linha você deverá resgatar a lista de Pictures que foi passada
        this.pictures = this.getIntent().getParcelableArrayListExtra(ListPicturesActivity.LIST_PICTURE_KEY);
        //A chave para acesso a lista é ListPicturesActivity.LIST_PICTURE_KEY
        if(this.pictures != null) {
            this.rvPictures = (RecyclerView) findViewById(R.id.rvPictures);
            this.pictureAdapter = new PictureAdapter(this.pictures, this);

            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
            int spanCount = (int) java.lang.Math.ceil(dpWidth / 100.0);

            this.rvPictures.setLayoutManager(new GridLayoutManager(this, spanCount));
            this.rvPictures.setHasFixedSize(true);
            this.rvPictures.setAdapter(this.pictureAdapter);
        }
    }

    @Override
    public void onClickPicture(Bitmap bitmap) {
        // Aqui você deverá chamar/mostrar um DialogFragment contendo a imagem clicada
        PictureDialogFragment pictureDialogFragment = PictureDialogFragment.newInstance(bitmap);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        pictureDialogFragment.show(fragmentManager, "showpic");
    }
}
