package oopprojet.tranlinh.com.oop_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class NhanHieu extends AppCompatActivity {
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

    private String tuKhoa,danhMuc,kichCo,trangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_hieu);
        tuKhoa = new String(main.keyWordText.getText()+"");
//        danhMuc = String.valueOf(main.button.getText()+"");
        danhMuc = String.valueOf(main.button.getText()+"");
        kichCo = String.valueOf(main.button3.getText()+"");
        trangThai = String.valueOf(main.statusBtn.getText()+"");
        gia = xuLyGia.CatXau(main.PriceText.getText()+"");
        //main.button3.setText(String.valueOf(gia[0])+"");
        test = db.getNH(tuKhoa,danhMuc,kichCo,gia[0],gia[1],trangThai);
        if(test.isEmpty()){
            test.add("Không có danh mục phù hợp");
        }
        listViewDanhMuc = (ListView)findViewById(R.id.listViewNhanHieu);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,test);
        listViewDanhMuc.setAdapter(adapter);
        listViewDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("noiDung", (Serializable) test.get(position));
                setResult(2,intent);
                NhanHieu.this.finish();
//                danhMuc.button.setText(arr[position]);
            }
        });
    }
}
