package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project.Adapter.PopularAdapter;
import com.example.project.Domain.CategoryDomain;
import com.example.project.Domain.ItemDomain;
import com.example.project.LayoutManager.AutoFitGridLayoutManager;
import com.example.project.R;

import java.util.ArrayList;

public class CategoryItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_item);

        CategoryDomain categoryDomain = (CategoryDomain) getIntent().getSerializableExtra("object");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(categoryDomain.getTitle());
        setSupportActionBar(toolbar);

        AutoFitGridLayoutManager gridLayoutManager = new AutoFitGridLayoutManager(this, 142);
        RecyclerView rvCategoryItems = findViewById(R.id.rvCategoryItems);
        rvCategoryItems.setLayoutManager(gridLayoutManager);

        ArrayList<ItemDomain> foodList = new ArrayList<>();
        if (categoryDomain.getTitle().equals("Aksesoris")) {
            foodList.add(new ItemDomain("Mouse Rexus X5 6d", "rexus_x5_6d", "MOUSE GAMING : Professional 2.4GHz Optical Wireless GAMING MOUSE Compatible USB Button Gaming Mouse Gaming Mouse Computer Mouse For PC Laptop with mini US Produk direct import dari pabrik OS Mac, Vista, Windows 7-10 Warna : MERAH, ABU2, BIRU, GOLD, HITAM Mohon no complaint Kualitas - Baik bgt. Compatible Windows 7-10, Vista, Mac Desktop PC, Laptop,Notebook 5 million clicks", 31000));
            foodList.add(new ItemDomain("Mouse NYK G07", "nyk_g07", "NYK G-07 Mouse adalah mouse gaming yang memiliki 4 level DPI (800/1200/1600/2400). Mouse ini juga dilengkapi fitur Rapid Fire Button. Rapid Fire Button berfungsi untuk melakukan double click hanya dengan satu klik. Mouse ini juga memiliki desain yang ergonomis sehingga sangat nyaman saat digenggam.", 75000));
            foodList.add(new ItemDomain("Headset NYK HS N01", "nyk_hs_n01", "Headset gaming Explore full size shinning professional gaming headset. Dual head band design, retractable head band, led power light, soft leather pad, super sensitive microphone.", 155000));
            foodList.add(new ItemDomain("Headset NYK HS N 05", "nyk_hs_n05", "Speaker Diameter: 40 mm Impedance; 32 Ω at 1kHz Sensitivity: 105 dB ± 3 dB SPL @ 1kHz Frequency Response: 20 Hz – 20 kHz Maximum power: 80 Mw Microphone: 6 x 5/-58 dB ± 2 dB", 134000));
            foodList.add(new ItemDomain("Keyboard NYK TKL 06", "nyk_tkl_06", "support-windows 7/ windows 8/ windows 10/ windows 10pro connection type - USB Wired button press life - Up To 10 millioms clicks rated voltage / current - DC 5V/<200mA cable length - 1.6m (PVC cable)", 129000));
            foodList.add(new ItemDomain("Headset Rexus 995", "rexus_995", "Rexus Vonix 995 HeadsetGaming Rexus Vonix 995 adalah professional gaming headset dengan desain ikat kepala teleskopik yang ergonomis yang memiliki ukuran speaker cukup besar dengan keamanan yang tinggi respon frekuensi yang luas dan bass yang transparan.", 79000));
            foodList.add(new ItemDomain("Keyboard Rexus K9d", "rexus_k9d", "Rexus Battlefire K9D merupakan keyboard gaming semi mekanik dengan 104 tombol bercahaya dari 3 warna LED, Hijau, Merah dan Biru, yang dapat kalian sesuaikan, serta fitur teknologi 19 tombol Anti Ghost, dengan tampilan alumunium alloy membuat keyboard ini menjadi teman terbaik untuk kalian para gamers dan professional.", 205000));
            foodList.add(new ItemDomain("Keyboard Rexus K1", "rexus_k1", "Rexus Battlefire K9D merupakan keyboard gaming semi mekanik dengan 104 tombol bercahaya dari 3 warna LED, Hijau, Merah dan Biru, yang dapat kalian sesuaikan, serta fitur teknologi 19 tombol Anti Ghost, dengan tampilan alumunium alloy membuat keyboard ini menjadi teman terbaik untuk kalian para gamers dan professional.", 154000));
            foodList.add(new ItemDomain("Headset NYK HS N 05", "nyk_hs_n05", "Speaker Diameter: 40 mm Impedance; 32 Ω at 1kHz Sensitivity: 105 dB ± 3 dB SPL @ 1kHz Frequency Response: 20 Hz – 20 kHz Maximum power: 80 Mw Microphone: 6 x 5/-58 dB ± 2 dB", 134000));
            foodList.add(new ItemDomain("Keyboard NYK TKL 06", "nyk_tkl_06", "support-windows 7/ windows 8/ windows 10/ windows 10pro connection type - USB Wired button press life - Up To 10 millioms clicks rated voltage / current - DC 5V/<200mA cable length - 1.6m (PVC cable)", 129000));
            foodList.add(new ItemDomain("Headset Rexus 995", "rexus_995", "Rexus Vonix 995 HeadsetGaming Rexus Vonix 995 adalah professional gaming headset dengan desain ikat kepala teleskopik yang ergonomis yang memiliki ukuran speaker cukup besar dengan keamanan yang tinggi respon frekuensi yang luas dan bass yang transparan.", 79000));
            foodList.add(new ItemDomain("Keyboard Rexus K9d", "rexus_k9d", "Rexus Battlefire K9D merupakan keyboard gaming semi mekanik dengan 104 tombol bercahaya dari 3 warna LED, Hijau, Merah dan Biru, yang dapat kalian sesuaikan, serta fitur teknologi 19 tombol Anti Ghost, dengan tampilan alumunium alloy membuat keyboard ini menjadi teman terbaik untuk kalian para gamers dan professional.", 205000));
        } else if (categoryDomain.getTitle().equals("CCTV")) {
            foodList.add(new ItemDomain("Kamera CCTV 5MP", "cctv_5mp", "Camera outdoor spc 5mp Body plastik berkualitas Garansi Resmi 1 tahun", 195000));
            foodList.add(new ItemDomain("Kamera CCTV Indoor", "cctv_indoor", "Resolusi 1920x1080 / 2 Megapixel real Lens 3.6mm Jarak IR 15 meter Power DC12V", 140000));
            foodList.add(new ItemDomain("Kamera IP CCTV 360", "ip_cctv_360", "Baby Cam atau Wireless IP Camera Setup gampang dengan koneksi PP2P ke Internet bisa online dari mana saja. (syarat : ada koneksi internet)", 225000));
            foodList.add(new ItemDomain("Jack XLR Male", "jack_xlr_male", "Kabel jack XLR High Perfomance panjang kabel 3 meter full kabel canare L2T-2S JAPAN STANDARD", 69000));
            foodList.add(new ItemDomain("Kabel CCTV RG 6", "kabel_cctv_rg_6", "Kabel rg6 plus power SPC Panjang 300 meter cocok untuk outdoor untuk tarikan panjang bagus", 775000));
            foodList.add(new ItemDomain("Konektor Kamera", "konektor_kamera", "Kabel rg6 plus power SPC Panjang 300 meter cocok untuk outdoor untuk tarikan panjang bagus", 1500));
            foodList.add(new ItemDomain("SPC Baby Cam", "spc_baby_cam", "Kamera Robot Wireless/Wireless IP Camera cocok dipasang di kantor atau rumah. Dapat dicontrol melalui smartphone sehingga anda dapat mengawasi kinerja karyawan di kantor atau pembantu & anak-anak di rumah", 225000));
            foodList.add(new ItemDomain("Jack XLR Male", "jack_xlr_male", "Kabel jack XLR High Perfomance panjang kabel 3 meter full kabel canare L2T-2S JAPAN STANDARD", 69000));
            foodList.add(new ItemDomain("Kabel CCTV RG 6", "kabel_cctv_rg_6", "Kabel rg6 plus power SPC Panjang 300 meter cocok untuk outdoor untuk tarikan panjang bagus", 775000));
            foodList.add(new ItemDomain("Konektor Kamera", "konektor_kamera", "Kabel rg6 plus power SPC Panjang 300 meter cocok untuk outdoor untuk tarikan panjang bagus", 1500));
            foodList.add(new ItemDomain("SPC Baby Cam", "spc_baby_cam", "Kamera Robot Wireless/Wireless IP Camera cocok dipasang di kantor atau rumah. Dapat dicontrol melalui smartphone sehingga anda dapat mengawasi kinerja karyawan di kantor atau pembantu & anak-anak di rumah", 225000));
            foodList.add(new ItemDomain("SPC Baby Cam", "spc_baby_cam", "Kamera Robot Wireless/Wireless IP Camera cocok dipasang di kantor atau rumah. Dapat dicontrol melalui smartphone sehingga anda dapat mengawasi kinerja karyawan di kantor atau pembantu & anak-anak di rumah", 225000));
        } else {
            foodList.add(new ItemDomain("Marcusy MW302R", "marcusy_mw302r", "300Mbps Wireless N Router ( MW302R ) Power Adapter Quick Installation Guide Ethernet Cable.", 127000));
            foodList.add(new ItemDomain("TP-Link TL-WR840N", "tplink_tl_wr840n", "300Mbps data wireless data nirkabel yang ideal untuk kedua tugas sensitif bandwidth dan pekerjaan dasar Enkripsi keamanan wireless yang mudah dengan menekan Tombol WPS.", 147000));
            foodList.add(new ItemDomain("Kabel Poe", "kabel_poe", "Poe spliter memudahkan untuk pemasangan ip cam, Network Access Point & Router-Router suport untuk semua ip cam, AP dan Router yng menggunakan adaptor sesuai alatnya ukuran jack 5mm.", 12999));
            foodList.add(new ItemDomain("Crimping Tool", "crimping_tool", "Crimping Tools ini berguna untuk menyambungkan Konektor RJ-45 dengan kabel jaringan Komputer yaitu kabel UTP Cat5e.", 39000));
            foodList.add(new ItemDomain("Marcusy MW302R", "marcusy_mw302r", "300Mbps Wireless N Router ( MW302R ) Power Adapter Quick Installation Guide Ethernet Cable.", 127000));
            foodList.add(new ItemDomain("TP-Link TL-WR840N", "tplink_tl_wr840n", "300Mbps data wireless data nirkabel yang ideal untuk kedua tugas sensitif bandwidth dan pekerjaan dasar Enkripsi keamanan wireless yang mudah dengan menekan Tombol WPS.", 147000));
            foodList.add(new ItemDomain("Kabel Poe", "kabel_poe", "Poe spliter memudahkan untuk pemasangan ip cam, Network Access Point & Router-Router suport untuk semua ip cam, AP dan Router yng menggunakan adaptor sesuai alatnya ukuran jack 5mm.", 12999));
            foodList.add(new ItemDomain("Crimping Tool", "crimping_tool", "Crimping Tools ini berguna untuk menyambungkan Konektor RJ-45 dengan kabel jaringan Komputer yaitu kabel UTP Cat5e.", 39000));
            foodList.add(new ItemDomain("Marcusy MW302R", "marcusy_mw302r", "300Mbps Wireless N Router ( MW302R ) Power Adapter Quick Installation Guide Ethernet Cable.", 127000));
            foodList.add(new ItemDomain("TP-Link TL-WR840N", "tplink_tl_wr840n", "300Mbps data wireless data nirkabel yang ideal untuk kedua tugas sensitif bandwidth dan pekerjaan dasar Enkripsi keamanan wireless yang mudah dengan menekan Tombol WPS.", 147000));
            foodList.add(new ItemDomain("Kabel Poe", "kabel_poe", "Poe spliter memudahkan untuk pemasangan ip cam, Network Access Point & Router-Router suport untuk semua ip cam, AP dan Router yng menggunakan adaptor sesuai alatnya ukuran jack 5mm.", 12999));
            foodList.add(new ItemDomain("Crimping Tool", "crimping_tool", "Crimping Tools ini berguna untuk menyambungkan Konektor RJ-45 dengan kabel jaringan Komputer yaitu kabel UTP Cat5e.", 39000));
        }

        rvCategoryItems.setAdapter(new PopularAdapter(foodList));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}