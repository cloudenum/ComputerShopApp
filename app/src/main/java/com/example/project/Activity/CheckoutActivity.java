package com.example.project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Domain.CityModel;
import com.example.project.Domain.DeliveryModel;
import com.example.project.Domain.FoodDomain;
import com.example.project.Helper.DeliverManagement;
import com.example.project.Helper.ManagementCart;
import com.example.project.Helper.TinyDB;
import com.example.project.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.*;

public class CheckoutActivity extends AppCompatActivity implements OnItemSelectedListener{

    Button btnBayar;
    TextView txtTotBelanja, txtShippingFee, txtTotPembayaran;
    Spinner spinPengiriman, spinBank;

    ManagementCart managementCart;
    DeliverManagement deliverManagement;
    TinyDB tinyDB;

    int totBelanja;
    String metodePembayaran ="-1", jenisPengiriman = "-1";
    String[] Bank;

    private final String TAG = "RSP1";
    int fees = 0;

    ArrayList<CityModel> list = new ArrayList<>();
    ArrayList<DeliveryModel> deliver = new ArrayList<>();
    ArrayList<FoodDomain> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        managementCart = new ManagementCart(this);
        deliverManagement = new DeliverManagement(this);
        tinyDB = new TinyDB(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        btnBayar = findViewById(R.id.btnBayar);
        txtTotBelanja = findViewById(R.id.txtTotalPrice);
        spinPengiriman = findViewById(R.id.spinListPengiriman);
        spinBank = findViewById(R.id.spinListMetode);
        txtShippingFee = findViewById(R.id.txtShippingFee);
        txtTotPembayaran = findViewById(R.id.txtTotPembayaran);

        btnBayar.setOnClickListener(v -> {
//            Log.d(TAG, "onCreate77: " + jenisPengiriman);
            if(!jenisPengiriman.equals("-1") && !metodePembayaran.equals("-1")){
//                Log.d(TAG, "onCreate: SHOW 1");
                int _id = tinyDB.getInt("ID");
                if(_id == 0) _id = 0;

                _id+=1;
                tinyDB.putInt("ID", _id);
                Log.d(TAG, "onCreate:77 " + _id);
                Log.d(TAG, "onCreate:77 " +fees);

                items = deliverManagement.copyList(_id, fees);
                Log.d(TAG, "onCreate:77 " + items.get(0).getFee());
                deliverManagement.insertToDelivering(items);

                Intent intent = new Intent(v.getContext(), CountdownPaymentActivity.class);
                intent.putExtra("METODE_PPEMBAYARAN", metodePembayaran);
                v.getContext().startActivity(intent);
            }else{
//                Log.d(TAG, "onCreate: SHOW");
                Toast.makeText(CheckoutActivity.this, "mohon pilih metode pembayaran dan jenis pengiriman", Toast.LENGTH_LONG).show();
            }
        });

        totBelanja = managementCart.getTotalPrice();

        txtTotBelanja.setText("Rp."+totBelanja);
        txtShippingFee.setText("-");

        txtTotPembayaran.setText("Rp. "+TotalPembayaran(0));

        spinPengiriman.setAdapter(SpinAdapter(R.array.jenis_pengiriman));
        spinBank.setAdapter(SpinAdapter(R.array.metode_pembayaran));
        spinPengiriman.setOnItemSelectedListener(this);
        spinBank.setOnItemSelectedListener(this);

        fortest();
        getApi();

        Bank = new String[]{"BRI", "BCA", "Mandiri", "BNI"};
    }

    private ArrayAdapter<CharSequence> SpinAdapter(int list){
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(
                this, list, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return Adapter;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if(parent.getId() == R.id.spinListPengiriman) {
            String jenis = parent.getSelectedItem().toString();
            int weight = 1;

            DeliveryModel dm = new DeliveryModel();

            String destination = getId("Cirebon", "Jawa Barat");
            String origin = getId("Dairi", "Sumatera Utara");

//            Log.d(TAG, "onItemSelected11: " + origin);
//            Log.d(TAG, "onItemSelected11: " + destination);

            if(jenis.equals("JNE")){
                jenisPengiriman = "JNE";
                getCostDelivery(origin,destination,"jne",weight);
            }else if(jenis.equals("POS")){
                jenisPengiriman = "POS";
                getCostDelivery(origin,destination,"pos",weight);
            }else if(jenis.equals("TIKI")) {
                jenisPengiriman = "TIKI";
                getCostDelivery(origin, destination, "tiki", weight);
            }else {
                jenisPengiriman="-1";
            }
        }
        else if(parent.getId() == R.id.spinListMetode) {
            String metode = parent.getSelectedItem().toString();

            for (int i = 0; i < Bank.length; i++){
                if(metode.equals(Bank[i])){
                    metodePembayaran = Bank[i];
                    break;
                }else {
                    metodePembayaran="-1";
                }
            }
        }

    }

    private void getCostDelivery(String origin, String destination, String courier, int weight){
        String param = "origin="+origin+"&destination="+destination+"&weight="+weight+"&courier="+courier;

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, param);
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/cost")
                .post(body)
                .addHeader("key", "330f894d1236fe7751edc831d599cb3c")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("RESPOM ERROR", "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();
                Log.d(TAG, "onResponse1: "+result);

                try {
                    JSONObject res = new JSONObject(result);
                    JSONObject rajaongkir = res.getJSONObject("rajaongkir");
                    JSONArray results = rajaongkir.getJSONArray("results");
                    JSONObject val = results.getJSONObject(0);
                    JSONArray costs = val.getJSONArray("costs");

                    Log.d(TAG, "onResponse54: "+costs.length());

                    for(int i = 0; i < costs.length(); i++){
                        DeliveryModel mo = new DeliveryModel();
                        JSONObject val_costs = costs.getJSONObject(i);
                        JSONArray cost = val_costs.getJSONArray("cost");
                        JSONObject val_cost = cost.getJSONObject(0);

                        Log.d(TAG, "onResponse55: " + deliver.size());

                        mo.setService(val_costs.getString("service"));
                        mo.setCost(val_cost.getInt("value"));
                        mo.setEstimate(val_cost.getString("etd"));

                        deliver.add(mo);
                    }
                }catch (JSONException e){
                    Log.e("JSON1 ERROR: ", "onResponse: "+e.getLocalizedMessage() );
                }

                int selected = 0;
                DeliveryModel m = new DeliveryModel();
                DeliveryModel m1 = new DeliveryModel();

                Log.d(TAG, "cost22: " + deliver.size());

                if(deliver.size() > 0){
                    for(int i = 0; i < deliver.size()-1; i++){
                        m = deliver.get(i);
                        m1 = deliver.get(i+1);

                        if(m.getCost() < m1.getCost()){
                            selected = i;
                        }else{
                            selected = i+1;
                        }
                    }
                }

                m = deliver.get(selected);
                final int fee = m.getCost();
                fees = fee;

                CheckoutActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtShippingFee.setText("Rp."+fee);
                        txtTotPembayaran.setText("Rp. "+TotalPembayaran(fee));
                    }
                });
            }
        });

        Log.d(TAG, "onResponse3: "+ list.size());
    }

    private int TotalPembayaran(int fee){
        return totBelanja + fee;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void getApi(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city")
                .get()
                .addHeader("key", "330f894d1236fe7751edc831d599cb3c")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("RESPOM ERROR", "onFailure: " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String result = response.body().string();

                try {
                    JSONObject res = new JSONObject(result);
                    JSONObject rajaongkir = res.getJSONObject("rajaongkir");
                    JSONArray results = rajaongkir.getJSONArray("results");

                    for(int j = 0; j < results.length(); j++){
                        CityModel model = new CityModel();
                        JSONObject val = results.getJSONObject(j);
                        model.setCityId(val.getString("city_id"));
                        model.setProvinceId(val.getString("province_id"));
                        model.setProvince(val.getString("province"));
                        model.setCity(val.getString("city_name"));
                        model.setPostCode(val.getString("postal_code"));

                        list.add(model);
                    }
                }catch (JSONException e){
                    Log.e("JOSN ERROR: ", "onResponse: "+e.getLocalizedMessage() );
                }
            }
        });
        Log.d(TAG, "getApi11: "+list.size());
    }

    private String getId(String city, String province){
        String ra = "";
        for(int i = 0; i < list.size(); i++){
            CityModel m = list.get(i);

            if(m.getCity().equals(city) && m.getProvince().equals(province)){
                ra = m.getCityId();
                break;
            }
        }

        Log.d(TAG, "getId99: " + ra);
        return ra;
    }

    private void fortest(){
        tinyDB.putString("kota", "Bantul");
        tinyDB.putString("provinsi", "DI Yogyakarta");
    }

    private void showShippingPrice(DeliveryModel m){
        Log.d(TAG, "showShippingPrice: OK " + m.getCost());
        txtShippingFee.setText(m.getCost()+"");
    }

    private DeliveryModel cost(){
        int selected = 0;
        DeliveryModel m = new DeliveryModel();
        DeliveryModel m1 = new DeliveryModel();

        Log.d(TAG, "cost22: " + deliver.size());

        if(deliver.size() >= 1){
            for(int i = 0; i < deliver.size(); i++){
                m = deliver.get(i);
                m1 = deliver.get(i+1);

                if(m.getCost() < m1.getCost()){
                    selected = i;
                }else{
                    selected = i+1;
                }
            }
        }

        m = deliver.get(selected);
        return m;
    }
}