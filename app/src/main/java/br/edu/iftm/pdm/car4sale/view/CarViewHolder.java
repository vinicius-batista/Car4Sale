package br.edu.iftm.pdm.car4sale.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.edu.iftm.pdm.car4sale.R;
import br.edu.iftm.pdm.car4sale.model.Car;

public class CarViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    private CarAdapter.CarListener carListener;
    private Car car;
    private TextView txtModelVH;
    private TextView txtBrandVH;
    private TextView txtPriceVH;

    public CarViewHolder(@NonNull View itemView, CarAdapter.CarListener carListener) {
        super(itemView);
        this.carListener = carListener;
        this.txtModelVH = (TextView) itemView.findViewById(R.id.txtModelVH);
        this.txtBrandVH = (TextView) itemView.findViewById(R.id.txtBrandVH);
        this.txtPriceVH = (TextView) itemView.findViewById(R.id.txtPriceVH);
        itemView.setOnClickListener(this);
    }

    public void bind(Car car) {
        this.car = car;
        this.txtModelVH.setText(car.getModel());
        this.txtBrandVH.setText(car.getBrand());
        this.txtPriceVH.setText(Float.toString(car.getPrice()));
    }

    @Override
    public void onClick(View view) {
        this.carListener.onClickCar(this.car);
    }
}
