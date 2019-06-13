package br.edu.iftm.pdm.car4sale;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CompoundButton;
import android.widget.Switch;
import java.util.ArrayList;
import br.edu.iftm.pdm.car4sale.data.DAOCar;
import br.edu.iftm.pdm.car4sale.data.DBHelper;
import br.edu.iftm.pdm.car4sale.model.Car;
import br.edu.iftm.pdm.car4sale.view.CarAdapter;

public class ListCarsActivity extends AppCompatActivity implements CarAdapter.CarListener, CompoundButton.OnCheckedChangeListener {

    private RecyclerView rvCars;
    private CarAdapter carAdapter;
    private ArrayList<Car> cars;
    private Switch swiFilter;

    private static final int REQUEST_CAR_EDIT_CODE = 1243;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cars);
        this.swiFilter = (Switch) findViewById(R.id.swiFilter);
        this.rvCars = (RecyclerView) findViewById(R.id.rvCars);
        this.cars = DAOCar.getAll(new DBHelper(this));
        this.carAdapter = new CarAdapter(this.cars, this);
        this.rvCars.setLayoutManager(new LinearLayoutManager(this));
        this.rvCars.setHasFixedSize(true);
        this.rvCars.setAdapter(this.carAdapter);
        this.swiFilter.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClickCar(Car car) {
        Intent intent = new Intent(this, ShowCarActivity.class);
        intent.putExtra(ShowCarActivity.CAR_KEY, car);
        startActivityForResult(intent, REQUEST_CAR_EDIT_CODE);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        // Este método funcionava :'(
        // Sua função é reconhecer quando o "Switch" é alterado de posição.
        // Experimente o parâmetro "b" dessa função
        // Aqui você deverá filtrar o conteúdo do recyclerview para mostrar somente os carros a venda
        this.carAdapter.filterOnSaleOnly(b);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CAR_EDIT_CODE && resultCode == RESULT_OK && data != null) {
            Car car = data.getParcelableExtra(ShowCarActivity.SOLD_KEY);
            if(car != null) {
                this.carAdapter.changeToSold(car);
            }
            car = data.getParcelableExtra(ShowCarActivity.CHANGE_DISCOUNT_KEY);
            if(car != null) {
                this.carAdapter.changeDiscount(car);
            }
            this.swiFilter.setChecked(false);
            this.carAdapter.filterOnSaleOnly(false);
        }
    }
}
