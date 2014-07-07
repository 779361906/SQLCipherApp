package com.example.sqlcipherapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import net.sqlcipher.*;
import net.sqlcipher.database.SQLiteDatabase;

public class MainActivity extends Activity {
	private SQLiteDatabase db;
	/**
	 *  show toast message
	 * @param message this is your message
	 */
	private void ShowToast(String message){
		Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
		toast.show();
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase.loadLibs(this);
        MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this, "mydb1.db", null, 1);
        db = dbHelper.getWritableDatabase("your_key");
        Button add = (Button) findViewById(R.id.add);
        Button query = (Button) findViewById(R.id.query);
        Button update = (Button) findViewById(R.id.update);
        Button delete = (Button) findViewById(R.id.delete);
        final TextView showText = (TextView) findViewById(R.id.textView2);
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			ContentValues values = new ContentValues();
			values.put("name", "one");
			values.put("age", 20);
			if(db.insert("Team", null, values) != -1){
				ShowToast("添加成功");
				Log.d("mydb","insert data name:one age:20");
			}else{
				ShowToast("添加失败");
			}
				
			}
		});
        
        query.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Cursor cursor = db.query("Team", null, null, null, null, null, null);
				if(cursor != null){
				showText.setText("查询结果："+"\n");
					while(cursor.moveToNext()){
						String name = cursor.getString(cursor.getColumnIndex("name"));
						int age = cursor.getInt(cursor.getColumnIndex("age"));
						long id = cursor.getLong(cursor.getColumnIndex("_id"));
						Log.d("mydb","name is:"+name+",age is:"+age+";"+";id:"+id );
						showText.setText(showText.getText().toString()+"name is:"+name+",age is:"+age+";"+"id:"+id+";\n");
					}
				}
				cursor.close();
			}
		});
       
        update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				ContentValues values = new ContentValues();
				values.put("name", "two");
				values.put("age", 200);
				String[] whereArgs={String.valueOf(20)}; 
				if(db.update("Team", values, "age=?",whereArgs) != -1){
					ShowToast("更新成功");
				}else{
					ShowToast("更新失败");
				}
			}
		});
        
        delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String whereClause = "age=?";  
				String[] whereArgs = {String.valueOf(20)};  
				if(db.delete("Team",whereClause,whereArgs) != 0){
					ShowToast("删除成功");
				}else{
					ShowToast("删除失败");
				}


			}
		});
        
    }

     
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
