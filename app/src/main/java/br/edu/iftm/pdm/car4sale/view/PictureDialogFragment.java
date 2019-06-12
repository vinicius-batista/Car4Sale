package br.edu.iftm.pdm.car4sale.view;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.edu.iftm.pdm.car4sale.R;


public class PictureDialogFragment extends DialogFragment {

    public static final String PIC_KEY = "br.edu.iftm.pdm.car4sale.picturedialogfragment.PIC";

    public static PictureDialogFragment newInstance(Bitmap bitmap) {
        Bundle args = new Bundle();
        args.putParcelable(PIC_KEY, bitmap);
        PictureDialogFragment fragment = new PictureDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_picture, container, false);
        ImageView imgOpenPic = (ImageView) view.findViewById(R.id.imgOpenPic);
        Bitmap bitmap = getArguments().getParcelable(PIC_KEY);
        imgOpenPic.setImageBitmap(bitmap);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }
}
