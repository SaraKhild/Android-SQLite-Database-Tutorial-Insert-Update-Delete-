package com.example.androidsqlitedatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText editText1 , editText2 , editText3;
    Button Addbtn , Showbtn , Updatebtn , Deletbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editText1 = findViewById(R.id.editTextFName);
        editText2 = findViewById(R.id.editTextSName);
        editText3= findViewById(R.id.editTextId);
        Addbtn = findViewById(R.id.addbtn);
        Showbtn = findViewById(R.id.Showbtn);
        Updatebtn = findViewById(R.id.Updatebtn);
        Deletbtn = findViewById(R.id.Deletbtn);


        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsert = myDb.insertData(editText1.getText().toString() , editText2.getText().toString());

                if( isInsert == true)
                    Toast.makeText(MainActivity.this, "Data Insert " , Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Insert " , Toast.LENGTH_LONG).show();
            }
        });


        Showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c  = myDb.getAllData();

                if( c.getCount()==0)
                {
                    // show message
                    showMessage("Error","Data not found");
                    return;

                }

                StringBuffer buffer = new StringBuffer(); // for store I think

                while (c.moveToNext())
                {

                    buffer.append("Id: " + c.getString(0)+"\n");
                    buffer.append("First Name: "+c.getString(1)+"\n");
                    buffer.append("Sconed Name: "+c.getString(2)+"\n\n");

                }

                    // show all data
                showMessage("Data",buffer.toString());


            }
        });


        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 boolean isUpdate = myDb.updateData(editText3.getText().toString(),editText1.getText().toString(),editText2.getText().toString());

                 if(isUpdate == true)
                     Toast.makeText(MainActivity.this, "Data is Updated " , Toast.LENGTH_LONG).show();
                 else
                     Toast.makeText(MainActivity.this, "Data not updated " , Toast.LENGTH_LONG).show();

            }
        });


        Deletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isdelete = myDb.deleteData(editText3.getText().toString());
                if(isdelete > 0)
                    Toast.makeText(MainActivity.this, "Data is deleted " , Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this, "Data not deleted " , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMessage ( String title , String message)

    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);

        // Initialize a new spannable string builder instance
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(title);

        // Apply the text color span
        ssBuilder.setSpan(
                foregroundColorSpan,
                0,
                title.length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );
        builder.setCancelable(true);
        builder.setTitle(ssBuilder);
        builder.setMessage(message);
        builder.setPositiveButton("OK",null);
        builder.show();

    }
}
