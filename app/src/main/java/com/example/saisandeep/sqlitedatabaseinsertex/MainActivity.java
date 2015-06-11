package com.example.saisandeep.sqlitedatabaseinsertex;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    SandeepDataBaseAdapter sandeepDataBaseAdapter;
    Button addUser,passwordDetails;
    EditText name,password,nameToGetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addUser= (Button) findViewById(R.id.button);
        name= (EditText) findViewById(R.id.editText);
        password= (EditText) findViewById(R.id.editText2);
        nameToGetPassword= (EditText) findViewById(R.id.editText3);
       // passwordDetails= (Button) findViewById(R.id.button3);

        sandeepDataBaseAdapter =new SandeepDataBaseAdapter(this);
    }

    public void addUser(View v)
    {
        String user=name.getText().toString();
        String pass=password.getText().toString();

        long id=sandeepDataBaseAdapter.insertData(user,pass);

        if(id<0)
        {

            Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Successfully inserted a row", Toast.LENGTH_SHORT).show();
        }
    }

    public void getDetails(View v)
    {

        String data=sandeepDataBaseAdapter.getAllData();
        Toast.makeText(this," "+data,Toast.LENGTH_LONG).show();
    }

    public void nameGivenPasswordDetails(View v)
    {
        String nameEntered=nameToGetPassword.getText().toString();
        String data2=sandeepDataBaseAdapter.getData(nameEntered);
        

        Toast.makeText(this,data2,Toast.LENGTH_LONG).show();
    }

    public void update(View v)
    {

        sandeepDataBaseAdapter.UpdateName("real","barca");
    }

    public void delete(View v)
    {

        sandeepDataBaseAdapter.deleteRow("bye");
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
