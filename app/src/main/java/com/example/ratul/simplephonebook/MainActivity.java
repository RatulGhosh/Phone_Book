package com.example.ratul.simplephonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvContact;
    ArrayList<ContactHolder> contactHolderArrayList;
    DB_Helper db_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = (ListView)findViewById(R.id.listView);
        db_helper = new DB_Helper(getApplicationContext());
        populateContactList();

    }

    void populateContactList(){
        contactHolderArrayList = db_helper.getContactList();
        lvContact.setAdapter(new ContactAdapter());

    }

    class ContactAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return contactHolderArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View layout = convertView;
            if(layout == null){
                layout = getLayoutInflater().inflate(R.layout.item_contact_list,parent,false);
            }

            TextView tvName = (TextView) layout.findViewById(R.id.textView);
            TextView tvPhone = (TextView) layout.findViewById(R.id.textView2);
            ContactHolder contactHolder = contactHolderArrayList.get(position);
            tvName.setText(contactHolder.name);
            tvPhone.setText(contactHolder.phone);
            return layout;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && resultCode == RESULT_OK){
            //result
            populateContactList();
            Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_contact) {
            startActivityForResult(new Intent(MainActivity.this, Add_contact.class), 100);
        }

        return super.onOptionsItemSelected(item);
    }
}
