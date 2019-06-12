package br.edu.iftm.pdm.car4sale.view;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.R;
import br.edu.iftm.pdm.car4sale.model.Picture;

public class PictureAdapter extends RecyclerView.Adapter<PictureViewHolder> {

    private PictureListener pictureListener;
    private ArrayList<Picture> pictures;

    public interface PictureListener {
        void onClickPicture(Bitmap bitmap);
    }

    public PictureAdapter(ArrayList<Picture> pictures, PictureListener pictureListener) {
        this.pictures = pictures;
        this.pictureListener = pictureListener;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_holder_picture, viewGroup, false);
        return new PictureViewHolder(view, this.pictureListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder pictureViewHolder, int i) {
        pictureViewHolder.bind(this.pictures.get(i));
    }

    @Override
    public int getItemCount() {
        return this.pictures.size();
    }
}
