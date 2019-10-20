package com.google.ai.regestrasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class list extends AppCompatActivity {
ListView listView;

    AlertDialog.Builder                      dialog;
List<Data> itemList =new ArrayList<Data>();
adapter adapter;
    DbHelper database = new DbHelper(this);
    public static final  String Tag_name = "name";
    public static final String Tag_user      = "user";
    public static final String Tag_sandi     = "sandi";
    public static final String Tag_email = "email";
    public static final String Tag_nohp = "nohp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        database = new DbHelper(getApplicationContext());

       listView = findViewById(R.id.list_view);
       adapter = new adapter(list.this,itemList);
       listView.setAdapter(adapter);
listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final String user = itemList.get(position).getUser();
        final String name = itemList.get(position).getName();
        final String email = itemList.get(position).getEmail();
        final String Nohp = itemList.get(position).getNohp();

        final CharSequence[] dialogitem = {"Detail", "Delete"};
       dialog     = new AlertDialog.Builder(list.this);
        dialog.setCancelable(true);
        dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent intent = new Intent(list.this, Main2Activity.class);
                        intent.putExtra(Tag_user, user);
                        intent.putExtra(Tag_name, name);
                        intent.putExtra(Tag_email, email);
                        intent.putExtra(Tag_nohp, Nohp);
                        startActivity(intent);
                        break;
                    case 1:
                        database.delete(name);
                        itemList.clear();
                        getData();
                        break;
                }
            }
        }).show();
        return false;
    }
});
getData();
    }
    private void getData(){
        ArrayList<HashMap<String, String>> row = database.getData();
        for (int i = 0; i < row.size();i++){
            String name = row.get(i).get(Tag_name);
            String user = row.get(i).get(Tag_user);
            String sandi = row.get(i).get(Tag_sandi);
            String email = row.get(i).get(Tag_email);
            String nohp = row.get(i).get(Tag_nohp);

            Data data = new Data();

            data.setName(name);
            data.setUser(user);
            data.setPass(sandi);
            data.setEmail(email);
            data.setNohp(nohp);

            itemList.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.logout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id==R.id.action_settings)
        {     SharedPreferences          sp = getSharedPreferences("com.google.ai.regestrasi", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString("User", "");
            ed.putString("Pass", "");
            ed.commit();
            startActivity(new Intent(this,Login.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
