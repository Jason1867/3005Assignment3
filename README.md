# 3005Assignment3
# Jason Xu 101220729
# Java PostgreSQL CRUD Application

This Java application connects to a PostgreSQL database to perform CRUD operations on a `students` table.

## Setup Instructions

1. **Database Setup:**
   - Have PostgreSQL installed and running.
   - Inserted initial data provided into the `students` table.

2. **Application Setup:**
   - Make sure Java is installed.

3. **Dependency Management:**
   - This project uses Maven for dependency management. Ensure you have Maven installed.
   - Add the PostgreSQL JDBC driver dependency to your `pom.xml` file:
     ```xml
     <dependencies>
         <dependency>
             <groupId>org.postgresql</groupId>
             <artifactId>postgresql</artifactId>
             <version>42.2.20</version>
         </dependency>
     </dependencies>
     ```

## Running the Application

1. **Compilation:**
   - Open your terminal or command prompt.
   - Navigate to the project directory.
   - Compile the Java code.

2. **Execution:**
   - Run the Java application.

3. **Expected Output:**
   - The application will perform CRUD operations on the `students` table and display the results in the console.

## Notes
- Replaced the placeholders at the top `database_name`, `username`, `password`, `host`, and `port` section with actual database credentials.
