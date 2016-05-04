package oopprojet.tranlinh.com.oop_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DanhMuc extends AppCompatActivity {
    private ListView listViewDanhMuc;
    private int[] gia = new int[2];
    private MainActivity main = new MainActivity();
    private XuLy xuLyGia = new XuLy();
    private ArrayList<SanPham> arrayList = new ArrayList<SanPham>();
//    final String arr[]={"Teo","Ty","Bin","Bo"};
    private ArrayList<String> test = new ArrayList<String>();
    private Bundle bundle = new Bundle();
    private Intent intent = new Intent();
    private Database db = new Database(this);

    private String tuKhoa,nhanHieu,kichCo,trangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        tuKhoa = new String(main.keyWordText.getText()+"");
//        danhMuc = String.valueOf(main.button.getText()+"");
        nhanHieu = String.valueOf(main.button2.getText()+"");
        kichCo = String.valueOf(main.button3.getText()+"");
        trangThai = String.valueOf(main.statusBtn.getText()+"");
        gia = xuLyGia.CatXau(main.PriceText.getText()+"");
        test = db.getDM(tuKhoa,nhanHieu,kichCo,gia[0],gia[1],trangThai);
        if(test.isEmpty()){
            test.add("Không có danh mục phù hợp");
        }
        listViewDanhMuc = (ListView)findViewById(R.id.listViewDanhMuc);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,test);
        listViewDanhMuc.setAdapter(adapter);
        listViewDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("noiDung", (Serializable) test.get(position));
                setResult(1,intent);
                DanhMuc.this.finish();
//                danhMuc.button.setText(arr[position]);
            }
        });
    }
}
