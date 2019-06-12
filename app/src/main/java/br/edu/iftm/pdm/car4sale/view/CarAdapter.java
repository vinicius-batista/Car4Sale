package br.edu.iftm.pdm.car4sale.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.R;
import br.edu.iftm.pdm.car4sale.model.Car;

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    private ArrayList<Car> cars;
    private ArrayList<Car> backupCars;
    private CarListener carListener;

    public CarAdapter(ArrayList<Car> cars, CarListener carListener) {
        this.cars = cars;
        this.backupCars = cars;
        this.carListener = carListener;
    }

    public interface CarListener{
        void onClickCar(Car car);
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.view_holder_car, viewGroup, false);
        return new CarViewHolder(view, this.carListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder carViewHolder, int i) {
        carViewHolder.bind(this.cars.get(i));
    }

    @Override
    public int getItemCount() {
        return this.cars.size();
    }

    public void filterOnSaleOnly(boolean onSaleOnly){
        // TODO use este método para filtrar os carros que estao a venda
        // Use o atributo backupCars para aulixiliá-lo neste método
    }

    public void changeToSold(Car carSold){
        for(Car car : this.cars) {
            if(car.getId() == carSold.getId())
                car.setOnSale(false);
        }
    }

    public void changeDiscount(Car carChanged){
        for(Car car : this.cars) {
            if(car.getId() == carChanged.getId())
                car.setPctDiscount(carChanged.getPctDiscount());
        }
    }
}
