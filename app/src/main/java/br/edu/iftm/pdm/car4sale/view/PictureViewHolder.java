package br.edu.iftm.pdm.car4sale.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import br.edu.iftm.pdm.car4sale.R;
import br.edu.iftm.pdm.car4sale.model.Picture;

public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private PictureAdapter.PictureListener pictureListener;
    private Bitmap picture;
    private ImageView imgPictureVH;

    public PictureViewHolder(@NonNull View itemView, PictureAdapter.PictureListener pictureListener) {
        super(itemView);
        this.imgPictureVH = (ImageView) itemView.findViewById(R.id.imgPictureVH);
        this.pictureListener = pictureListener;
        itemView.setOnClickListener(this);
    }

    public void bind(Picture picture) {
        // Aqui você deverá mostrar a imagem, que está no path de picture, no imgPictureVH.
        this.picture = BitmapFactory.decodeFile(picture.getPath());
        this.imgPictureVH.setImageBitmap(this.picture);
    }

    @Override
    public void onClick(View view) {
        this.pictureListener.onClickPicture(this.picture);
    }
}
