package com.italo.dbagenda;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private String name,add,city,state, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = DatabaseHandler.getmInstance(getApplicationContext());

        final EditText NAME = (EditText) findViewById(R.id.txtEmpName);
        final EditText Address = (EditText) findViewById(R.id.txtEmpAddress);
        final EditText City = (EditText) findViewById(R.id.txtEmpCity);
        final EditText State = (EditText) findViewById(R.id.txtEmpState);
        final EditText Mobile = (EditText) findViewById(R.id.txtEmpMobile);


        Button btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                name = NAME.getText().toString();
                add = Address.getText().toString();
                city = City.getText().toString();
                state = State.getText().toString();
                mobile = Mobile.getText().toString();

                long id = db.insertIntoEmpInfo(name,add,city,state, mobile);
                if (id>0){
                    Toast.makeText(getApplicationContext(), "Employee information saved successfuly.", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "No Employee information saved. ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
