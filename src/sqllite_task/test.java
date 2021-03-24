package sqllite_task;

import java.sql.*;
import java.util.ArrayList;


interface DAO_User<T> {
    void createUser(T user) throws SQLException;

    ArrayList<T> getAll() throws SQLException;

    T getPersonById(int id) throws SQLException, ModelNotFoundException;

    void updatePerson(T user) throws SQLException;

    void deletePerson(int id) throws SQLException;
}

class PersonDBO {

    public int id;
    public String name;
    public String address;
    public Date birthYear;

    @Override
    public String toString() {
        return "PersonDBO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}

class ModelNotFoundException extends Exception {
    ModelNotFoundException(String error) {
        super(error);
    }
}

class PersonController implements DAO_User<PersonDBO> {

    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:E:\\University\\sqllite\\sample.db");

        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
    }

    @Override
    public void createUser(PersonDBO user) throws SQLException {

        Statement statement = connection.createStatement();

        statement.executeUpdate("insert into person(name,address,birthday) values(" + "'" + user.name + "'" + "," + "'" + user.address + "'" + "," + user.birthYear + ")");
    }

    @Override
    public ArrayList<PersonDBO> getAll() throws SQLException {

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from person");

        ArrayList<PersonDBO> personDBOS = new ArrayList<>() {
        };

        while (rs.next()) {
            PersonDBO personDBO = new PersonDBO() {
                {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    address = rs.getString("address");
                    birthYear = rs.getDate("birthday");
                }
            };

            personDBOS.add(personDBO);
        }

        return personDBOS;
    }

    @Override
    public PersonDBO getPersonById(int id) throws SQLException, ModelNotFoundException {

        if (id <= 0) {
            throw new IllegalArgumentException("ID should be positive!");
        }

        Statement statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("select * from person where id =" + id);

        if (!rs.next()) {
            throw new ModelNotFoundException("Model with such ID was not found!");
        } else {
            return new PersonDBO() {
                {
                    id = rs.getInt("id");
                    name = rs.getString("name");
                    address = rs.getString("address");
                    birthYear = rs.getDate("birthday");
                }
            };
        }


    }

    @Override
    public void updatePerson(PersonDBO user) throws SQLException {

        if (user.id <= 0) {
            throw new IllegalArgumentException("ID should be positive!");
        }

        Statement statement = connection.createStatement();

        var rs = statement.executeUpdate("update person set name ='" + user.name + "', address ='" + user.address + "', birthday=" + user.birthYear + " where id =" + user.id);

        if (rs == 1) {
            System.out.println("Model was successfully updated!");
        }
    }

    @Override
    public void deletePerson(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("ID should be positive!");
        }

        Statement statement = connection.createStatement();

        int rs = statement.executeUpdate("delete from person where id =" + id);

        if (rs == 1) {
            System.out.println("Person with such ID was successfully deleted.");
        }

    }
}

public class test {

    public static void main(String[] args) {
        PersonController controller = new PersonController();

        //тестуємо метод getAll()
        System.out.println("||||||||||||||||GET ALL|||||||||||||||");
        try {
            var personArray = controller.getAll();

            for (var person : personArray) {
                System.out.println(person.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        //тестуємо метод getPersonById()
        System.out.println("|||||||||||||||GET BY ID|||||||||||||||||||");
        try {
            System.out.println(controller.getPersonById(1));

        } catch (SQLException | ModelNotFoundException e) {
            e.printStackTrace();
        }

        //тестуємо createPerson()
        System.out.println("|||||||||||||||CREATE|||||||||||||||||||");
        try {
            controller.createUser(new PersonDBO() {
                {
                    name = "Jogn";
                    address = "5TH Avenue";
                    birthYear = new Date(100000);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //тестуємо метод getPersonById()
        System.out.println("|||||||||||||||GET BY ID|||||||||||||||||||");
        try {
            System.out.println(controller.getPersonById(10));

        } catch (SQLException | ModelNotFoundException e) {
            e.printStackTrace();
        }

        //тестуємо createPerson()
        System.out.println("|||||||||||||||UPDATE|||||||||||||||||||");
        try {
            controller.updatePerson(new PersonDBO() {
                {
                    id = 10;
                    name = "John2222";
                    address = "aaaa";
                    birthYear = new Date(111525);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //тестуємо метод getAll()
        System.out.println("||||||||||||||||GET ALL|||||||||||||||");
        try {
            var personArray = controller.getAll();

            for (var person : personArray) {
                System.out.println(person.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //тестуємо метод delete()
        try {
            controller.deletePerson(10);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            controller.connection.close();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}

class Sample {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:E:\\University\\sqllite\\sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet rs = statement.executeQuery("select * from person");
            while (rs.next()) {
                // read the result set
                System.out.println("name = " + rs.getString("name"));
                System.out.println("id = " + rs.getInt("id"));
                System.out.println("address = " + rs.getString("address"));
            }
        } catch (SQLException e) {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
