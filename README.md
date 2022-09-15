# UsersManagement
User and Password administration (JDBC)
___

##Notes

- The database was made in MySQL. The script is in `resources` package.
- Using dependencies with Maven
- Using Design Patterns: DAO, Singleton
- Using transaction for `addUser()`  method
- Using custom Exceptions classes


##Behavior

- Insert, Update, Delete and Select (byId, ByUsername) Users

| id_user  | username | password |
|:---------|:---------|:---------|
| `int AI` | `String` | `String` |

- If User that you wanted to add already exists, new user replace it