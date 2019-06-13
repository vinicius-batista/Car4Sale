package br.edu.iftm.pdm.car4sale;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.iftm.pdm.car4sale.data.DAOCar;
import br.edu.iftm.pdm.car4sale.data.DBHelper;
import br.edu.iftm.pdm.car4sale.model.Car;
import br.edu.iftm.pdm.car4sale.view.CarDiscountDialogFragment;

public class ShowCarActivity extends AppCompatActivity {

    public static final String CAR_KEY = "br.edu.iftm.pdm.car4sale.showcar.CAR";
    public static final String SOLD_KEY = "br.edu.iftm.pdm.car4sale.showcar.SOLD";
    public static final String CHANGE_DISCOUNT_KEY = "br.edu.iftm.pdm.car4sale.showcar.CHANGE_DISCOUNT";


    private TextView txtModelValue;
    private TextView txtBrandValue;
    private TextView txtChassisValue;
    private TextView txtLicensePlateValue;
    private TextView txtPriceValue;
    private TextView txtPctDiscountValue;
    private TextView txtTotalPriceValue;

    private Button btnSold;

    private Car car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_car);

        this.car = getIntent().getParcelableExtra(CAR_KEY);

        this.txtModelValue = (TextView) findViewById(R.id.txtModelValue);
        this.txtBrandValue = (TextView) findViewById(R.id.txtBrandValue);
        this.txtChassisValue = (TextView) findViewById(R.id.txtChassisValue);
        this.txtLicensePlateValue = (TextView) findViewById(R.id.txtLicensePlateValue);
        this.txtPriceValue = (TextView) findViewById(R.id.txtPriceValue);
        this.txtPctDiscountValue = (TextView) findViewById(R.id.txtPctDiscountValue);
        this.txtTotalPriceValue = (TextView) findViewById(R.id.txtTotalPriceValue);
        this.btnSold = (Button) findViewById(R.id.btnSold);

        if(!car.isOnSale())
            this.btnSold.setVisibility(View.INVISIBLE);

        this.txtModelValue.setText(this.car.getModel());
        this.txtBrandValue.setText(this.car.getBrand());
        this.txtChassisValue.setText(this.car.getChassis());
        this.txtLicensePlateValue.setText(this.car.getLicensePlate());
        this.txtPriceValue.setText(String.format("%.2f", this.car.getPrice()));
        this.txtPctDiscountValue.setText(Integer.toString(this.car.getPctDiscount()));
        this.txtTotalPriceValue.setText(String.format("%.2f", this.car.getTotalPrice()));

    }

    public void onClickShowCarPictures(View view) {
        Intent intent = new Intent(this, ListPicturesActivity.class);
        // esta intent deverá encaminhar a lista de Pictures a ser mostrada
        intent.putExtra(ListPicturesActivity.LIST_PICTURE_KEY, this.car.getPictures());
        startActivity(intent);
    }

    public void onClickSold(View view) {
        this.car.setOnSale(false);
        DAOCar.sellCar(new DBHelper(this), this.car);
        Toast.makeText(this, getString(R.string.car_sold), Toast.LENGTH_SHORT).show();
        this.btnSold.setVisibility(View.INVISIBLE);
        Intent output = new Intent();
        output.putExtra(SOLD_KEY, this.car);
        this.setResult(RESULT_OK, output);
        finish();
    }

    public void onClickChangeDiscount(View view) {
        // Aqui você deverá chamar/mostrar um DialogFragment contendo a caixa de edição do desconto para este carro.
        CarDiscountDialogFragment carDiscountDialogFragment = CarDiscountDialogFragment.newInstance(car);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        carDiscountDialogFragment.show(fragmentManager, "showcardiscount");
    }
}
