package br.edu.iftm.pdm.car4sale.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.iftm.pdm.car4sale.R;
import br.edu.iftm.pdm.car4sale.ShowCarActivity;
import br.edu.iftm.pdm.car4sale.data.DAOCar;
import br.edu.iftm.pdm.car4sale.data.DBHelper;
import br.edu.iftm.pdm.car4sale.model.Car;

public class CarDiscountDialogFragment extends DialogFragment {

    public static final String CAR_KEY = "br.edu.iftm.pdm.car4sale.cardiscountdialogfragment.CAR";
    private EditText ptxtEditDiscountPercentage;
    private Car car;

    public static CarDiscountDialogFragment newInstance(Car car) {
        Bundle args = new Bundle();
        args.putParcelable(CAR_KEY, car);
        CarDiscountDialogFragment fragment = new CarDiscountDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.dialog_fragment_car_discount, null);
        this.ptxtEditDiscountPercentage = (EditText) view.findViewById(R.id.ptxtEditDiscountPercentage);
        this.car = getArguments().getParcelable(CAR_KEY);
        this.ptxtEditDiscountPercentage.setText(Integer.toString(this.car.getPctDiscount()));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String pctDiscountStr = ptxtEditDiscountPercentage.getText().toString();
                        if(!pctDiscountStr.isEmpty()) {
                            car.setPctDiscount(Integer.parseInt(pctDiscountStr));
                            DAOCar.changeDiscount(new DBHelper(getActivity()), car);
                            Intent output = new Intent();
                            output.putExtra(ShowCarActivity.CHANGE_DISCOUNT_KEY, car);
                            getActivity().setResult(getActivity().RESULT_OK, output);
                            Toast.makeText(getActivity(), getActivity().getText(R.string.discount_changed),Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), getActivity().getText(R.string.discount_not_changed),Toast.LENGTH_SHORT).show();
                    }
                });
        return builder.create();
    }
}
