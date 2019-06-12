package br.edu.iftm.pdm.car4sale;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.edu.iftm.pdm.car4sale.data.DAOCar;
import br.edu.iftm.pdm.car4sale.data.DBHelper;
import br.edu.iftm.pdm.car4sale.model.Car;
import br.edu.iftm.pdm.car4sale.model.Picture;

public class NewCarActivity extends AppCompatActivity {

    private Car car;
    private ArrayList<Picture> pictures;

    private EditText ptxtModel;
    private EditText ptxtBrand;
    private EditText ptxtChassis;
    private EditText ptxtLicensePlate;
    private EditText ptxtPrice;
    private EditText ptxtPctDiscount;
    private static final int REQUEST_TAKEN_PICTURES_CODE = 1982;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_car);

        this.ptxtModel = (EditText) findViewById(R.id.ptxtModel);
        this.ptxtBrand = (EditText) findViewById(R.id.ptxtBrand);
        this.ptxtChassis = (EditText) findViewById(R.id.ptxtChassis);
        this.ptxtLicensePlate = (EditText) findViewById(R.id.ptxtLicensePlate);
        this.ptxtPrice = (EditText) findViewById(R.id.ptxtPrice);
        this.ptxtPctDiscount = (EditText) findViewById(R.id.ptxtPctDiscount);

        this.car = new Car();
        this.pictures = new ArrayList<>();

    }

    public void onClickTakePictures(View view) {
        Intent intent = new Intent(this, TakePicturesActivity.class);
        intent.putExtra(TakePicturesActivity.PICTURES_KEY, this.pictures);
        // Junto a esta intent, você precisa passar o array de fotos tal como está, mesmo que esteja vazio.
        startActivityForResult(intent, REQUEST_TAKEN_PICTURES_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_TAKEN_PICTURES_CODE && resultCode == RESULT_OK && data != null) {
            // Aqui você receberá como resposta o array preenchido com os paths das imagens inseridas
            this.pictures = data.getParcelableArrayListExtra(TakePicturesActivity.PICTURES_KEY);
        }
    }

    public void onClickSave(View view) {
        // Aqui você deverá salvar o carro que foi cadastrado
        // Você não deverá permitir que nenhum campo esteja em branco, com exceção da lista de fotos
        // Isso também deverá implicar na forma como você criará a tabela no banco.

        String model = ptxtModel.getText().toString();
        String brand = ptxtBrand.getText().toString();
        String chassis = ptxtChassis.getText().toString();
        String licensePlate = ptxtLicensePlate.getText().toString();
        String price = ptxtPrice.getText().toString();
        String pctDiscount = ptxtPctDiscount.getText().toString();

        if (!model.isEmpty() && !brand.isEmpty() && !chassis.isEmpty() && !licensePlate.isEmpty()
                && !price.isEmpty() && !pctDiscount.isEmpty()) {
            this.car.setBrand(brand);
            this.car.setModel(model);
            this.car.setChassis(chassis);
            this.car.setLicensePlate(licensePlate);
            this.car.setPrice(Float.parseFloat(price));
            this.car.setPctDiscount(Integer.parseInt(pctDiscount));
            this.car.setOnSale(true);
            this.car.setPictures(this.pictures);

            DBHelper dbHelper = new DBHelper(this);

            DAOCar.insert(dbHelper, this.car);
            this.finish();
            return;
        }

        Toast.makeText(this, getText(R.string.empty_fields), Toast.LENGTH_SHORT).show();
    }
}
