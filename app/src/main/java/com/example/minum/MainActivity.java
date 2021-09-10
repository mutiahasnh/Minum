package com.example.minum;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;


public class MainActivity extends AppCompatActivity {

    int quantity=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void increment(View view){//perintah tombol tambah
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){//perintah tombol tambah
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox milkshakeChekBox= (CheckBox) findViewById(R.id.milkshake_cb);
        boolean hasmilkshake=milkshakeChekBox.isChecked();
        Log.v("MainActivity","has milkshae:"+hasmilkshake);

        CheckBox bobaChekBox= (CheckBox) findViewById(R.id.boba_cb);
        boolean hasboba=bobaChekBox.isChecked();
        Log.v("MainActivity","has boba:"+hasboba);

        CheckBox jusjerukChekBox= (CheckBox) findViewById(R.id.jusjeruk_cb);
        boolean hasjusjeruk=jusjerukChekBox.isChecked();
        Log.v("MainActivity","has whippedcream:"+hasjusjeruk);

        int price=calculateprice(hasmilkshake,hasboba,hasjusjeruk);//memanggil method jumlah harga
        String pricemessage=createOrderSummary(price,name,hasmilkshake,hasboba,hasjusjeruk);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean addmilkshake,boolean addboba, boolean addjusjeruk) {
        int harga=15000;

        if(addmilkshake){
            harga=harga+11000;
        }

        if (addboba){
            harga=harga+12000;
        }
        if (addjusjeruk) {
            harga = harga + 10000;
        }
        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean hasmilkshake, boolean addChocolate, boolean addWhippedCream) {//hasil pemesanan
        String pricemessage=" Nama = "+name;
        pricemessage+="\n Tambahkan Coklat =" +addWhippedCream;
        pricemessage+="\n Tambahkan Krim =" +addChocolate;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }

    //method ini untuk mencetak hasil perintah yang di tampilkan dengan inisial quantity_textview di textview 0
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}