//package com.anu.sample_dao;
//
//
//import androidx.annotation.NonNull;
//import androidx.room.ColumnInfo;
//import androidx.room.Dao;
//import androidx.room.Delete;
//import androidx.room.Insert;
//import androidx.room.OnConflictStrategy;
//import androidx.room.Query;
//import androidx.room.Update;
//
//import java.util.List;
//
//@Dao
//public interface MyDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    public void insertUsers(User... users);
//
//    @Insert
//    public void insertBothUsers(User user1, User user2);
//
//    @Insert
//    public void insertUsersAndFriends(User user, List<User> friends);
//
//    @Update
//    public void updateUsers(User... users);
//
//    @Delete
//    public void deleteUsers(User... users);
//
//
//    @Query("SELECT * FROM user")
//    public User[] loadAllUsers();
//
//    @Query("SELECT * FROM user WHERE age > :minAge")
//    public User[] loadAllUsersOlderThan(int minAge);
//
//
//
//    @Query("SELECT * FROM user WHERE age BETWEEN :minAge AND :maxAge")
//    public User[] loadAllUsersBetweenAges(int minAge, int maxAge);
//
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :search " +
//            "OR last_name LIKE :search")
//    public List<User> findUserWithName(String search);
//
//
//    @ColumnInfo(name = "first_name")
//    public String firstName;
//
//    @ColumnInfo(name = "last_name")
//    @NonNull
//    public String lastName;
//
//
//    @Query("SELECT first_name, last_name FROM user")
//    public List<NameTuple> loadFullName();
//
//
//    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
//    public List<NameTuple> loadUsersFromRegions(List<String> regions);
//
//
//    @Query("SELECT first_name, last_name FROM user WHERE region IN (:regions)")
//    public LiveData<List<User>> loadUsersFromRegionsSync(List<String> regions);
//}