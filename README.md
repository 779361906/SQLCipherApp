SQLCipherApp
============
*1.先了解下SQLCipher。SQLCipher是用于SQLite数据库加密的开源框架，在android中使用SQLCipher对SQLite数据库进行加密可有效保护用户数据。更多详情：http://sqlcipher.net/about

*2.使用SQLCipher的前期准备：下载编译好的库。地址：
https://s3.amazonaws.com/sqlcipher/SQLCipher+for+Android+v3.1.0.zip

*3.解压库的压缩包，将assets文件夹内的文件copy到你的项目中的assets中，将libs中的文件copy到项目的libs中。（armeabi文件夹，armeabi-v7a文件夹和x86文件夹分别对应不同的cpu指令平台，可根据需求选择）

*4.开始使用SQLCipher：
  *4.1 使用SQLCipher和正常的使用SQLite一样需要先创建SQLiteOpenHelper类，我创建的是MySQLiteOpenHelper类。
      首先需要 `import net.sqlcipher.database.SQLiteDatabase;
      import net.sqlcipher.database.SQLiteDatabase.CursorFactory;
      import net.sqlcipher.database.SQLiteOpenHelper;`
      注意：SQLiteOPenHelper类需要继承net.sqlcipher.database.SQLiteOpenHelper类而不是android自己的SQLiteOpenHelper。
      其他的和正常的使用SQLite是一样的。
  *4.2 在Activity_Main.java中使用
      import net.sqlcipher.database.SQLiteDatabase；和import net.sqlcipher.*;
      创建私有的SQLiteDatabases对象 --> private SQLiteDatabase db;
      这里的SQLiteDatabases对象使用的是net.sqlcipher.database.SQLiteDatabase，而不是android系统自带的
      在OnCreate里使用SQLiteDatabase.loadLibs(this);加载SQLCipher的库。
      使用MySQLiteOpenHelper类创建dbHelper对象
      MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(Context context, String name, CursorFactory factory, int version);
      
       db = dbHelper.getWritableDatabase("your_key");这里指示db对象，“your_key”是你用于加密的密钥
      
      剩下的增删改查操作和正常使用SQLite的操作是一样的。具体可见我的MainActivity.java类。
      注：我在MainActivity.java中使用了Toast做为操作响应监听，开发中可用Log.d替换。
       
      

