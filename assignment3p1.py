import psycopg2
from psycopg2 import Error

# Database connection parameters
DB_NAME = "assignment3"
DB_USER = "postgres"
DB_PASSWORD = "admin"
DB_HOST = "localhost"
DB_PORT = "5432"

def connect():
    """Establishes connection to PostgreSQL database."""
    try:
        connection = psycopg2.connect(
            user=DB_USER,
            password=DB_PASSWORD,
            host=DB_HOST,
            port=DB_PORT,
            database=DB_NAME
        )
        return connection
    except (Exception, Error) as error:
        print("Error while connecting to PostgreSQL", error)

def close_connection(connection):
    """Closes the database connection."""
    if connection:
        connection.close()

def getAllStudents():
    """Retrieves and displays all records from the students table."""
    try:
        connection = connect()
        cursor = connection.cursor()
        cursor.execute("SELECT * FROM students")
        students = cursor.fetchall()
        for student in students:
            print(student)
    except (Exception, Error) as error:
        print("Error while fetching data from PostgreSQL", error)
    finally:
        if connection:
            close_connection(connection)

def addStudent(first_name, last_name, email, enrollment_date):
    """Inserts a new student record into the students table."""
    try:
        connection = connect()
        cursor = connection.cursor()
        insert_query = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (%s, %s, %s, %s)"
        cursor.execute(insert_query, (first_name, last_name, email, enrollment_date))
        connection.commit()
        print("Student added successfully")
    except (Exception, Error) as error:
        print("Error while adding student to PostgreSQL", error)
    finally:
        if connection:
            close_connection(connection)

def updateStudentEmail(student_id, new_email):
    """Updates the email address for a student with the specified student_id."""
    try:
        connection = connect()
        cursor = connection.cursor()
        update_query = "UPDATE students SET email = %s WHERE student_id = %s"
        cursor.execute(update_query, (new_email, student_id))
        connection.commit()
        print("Email updated successfully")
    except (Exception, Error) as error:
        print("Error while updating email in PostgreSQL", error)
    finally:
        if connection:
            close_connection(connection)

def deleteStudent(student_id):
    """Deletes the record of the student with the specified student_id."""
    try:
        connection = connect()
        cursor = connection.cursor()
        delete_query = "DELETE FROM students WHERE student_id = %s"
        cursor.execute(delete_query, (student_id,))
        connection.commit()
        print("Student deleted successfully")
    except (Exception, Error) as error:
        print("Error while deleting student in PostgreSQL", error)
    finally:
        if connection:
            close_connection(connection)

# Example usage:
if __name__ == "__main__":
    print("All students:")
    getAllStudents()

    print("\nAdding a new student:")
    addStudent("Alice", "Johnson", "alice.johnson@example.com", "2023-09-03")
    getAllStudents()

    print("\nUpdating student's email:")
    updateStudentEmail(1, "john.newemail@example.com")
    getAllStudents()

    print("\nDeleting a student:")
    deleteStudent(3)
    getAllStudents()
