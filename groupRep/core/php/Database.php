<?php
class DataBase {

    private $conn;
    private $sql;
    private $sqlData;

    /**
     * create the connection whit the database
     */
    function __construct() {
        // make a connection whit the database
        $this->conn = new PDO("mysql:host=".DB_HOST.";dbname=".DB_NAME, DB_USER, DB_PASSWORD);
        // set the PDO error mode to chosen error mode
        $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        return $this;
    }

    /**
     * reset all the data before a statement begins and after the excecution of a statement
     */
    private function reset() {
        $this->sqlData = null;
        $this->sql = null;
        return $this;
    }

    public function createTable($tableName, $sql) {
        //make sure the targeted table is availble 
        try {
            $stmt = $this->conn->prepare("SHOW TABLES LIKE '".$tableName."';");
            $stmt->execute();
            if (count($stmt->fetchAll()) === 1) {
                return TRUE;//if not return true so that the place where it is called from knows that the excecuteStatement() call can't be made
            }
        } catch (Exception $exception) {
        }
        //if not create the table
        $this->reset()->sql = $sql;
        $this->sqlData['function'] = "create";
        $this->sqlData['type'] = "statement";
        return $this;
    }
    /**
     * start the insert statement
     */
    public function insert($table, $columns, $data) {
        $this->reset()->sqlData['type'] = "statement";
        $this->sqlData['function'] = "insert";

        if (count($columns) !== count($data)) {throw new Exception("columns and data are not equal", 4);}

        $insertColomns = "";
        for ($i = 0; $i < count($columns); $i++) {
            $insertColomns .= $columns[$i];
            $insertColomns .= count($data) !== ($i + 1) ? "," : "";
        }

        $insertData = "";
        for ($i = 0; $i < count($data); $i++) {
            $insertData .= gettype($data[$i]) === "string" ? "'" : "";
            $insertData .= $data[$i];
            $insertData .= gettype($data[$i]) === "string" ? "'" : "";
            $insertData .= count($data) !== ($i + 1) ? "," : "";
        }
        $this->sql = "INSERT INTO " .DB_NAME.".".$table. " (" .$insertColomns. ") VALUES (" .$insertData. ")";
        return $this;
    }

    /**
     * starts the select statement
     */
    public function select($table, $columns) {
        $this->reset()->sqlData['type'] = "query";
        $this->sqlData['function'] = "select";
        $selectColomns = "";
        for ($i = 0; $i < count($columns); $i++) {
            $selectColomns .= $columns[$i];
            $selectColomns .= count($columns) !== ($i + 1) ? "," : "";
        }
        $this->sql = "SELECT " .$selectColomns. " FROM " .DB_NAME.".".$table ." ";
        return $this;
    }

    /**
     * starts the update statement
     */
    public function update($table, $columns, $data) {
        $this->reset()->sqlData['type'] = "statement";
        $this->sqlData['function'] = "update";

        if (count($columns) !== count($data)) {throw new Exception("columns and data are not equal", 4);}

        $updateData = "";
        for ($i = 0; $i < count($data); $i++) {
            $updateData .= $columns[$i]. "=";
            $updateData .= gettype($data[$i]) === "string" ? "'" : "";
            $updateData .= $data[$i];
            $updateData .= gettype($data[$i]) === "string" ? "'" : "";
            $updateData .= count($data) !== ($i + 1) ? "," : "";
        }
        $this->sql = "UPDATE " .DB_NAME.".".$table. " SET ". $updateData;
        return $this;
    }

    /**
     * starts the insert on duplicate key update statement
     */
    public function insertOrUpdate($table, $columns, $data) {
        $this->reset()->sqlData['type'] = "statement";
        $this->sqlData['function'] = "insertOrUpdate";
        
        if (count($columns) !== count($data)) {throw new Exception("columns and data are not equal", 4);}

        $insertColomns = "";
        for ($i = 0; $i < count($columns); $i++) {
            $insertColomns .= $columns[$i];
            $insertData .= count($data) !== ($i + 1) ? "," : "";
        }

        $insertData = "";
        for ($i = 0; $i < count($data); $i++) {
            $insertData .= gettype($data[$i]) === "string" ? "'" : "";
            $insertData .= $data[$i];
            $insertData .= gettype($data[$i]) === "string" ? "'" : "";
            $insertData .= count($data) !== ($i + 1) ? "," : "";
        }

        $updateData = "";
        for ($i = 0; $i < count($data); $i++) {
            $updateData .= $columns[$i]. "=";
            $updateData .= gettype($data[$i]) === "string" ? "'" : "";
            $updateData .= $data[$i];
            $updateData .= gettype($data[$i]) === "string" ? "'" : "";
            $updateData .= count($data) !== ($i + 1) ? "," : "";
        }
        $this->sql = "INSERT INTO " .DB_NAME.".".$table. " (" .$insertColomns. ") VALUES (" .$insertData. ") ON DUPLICATE KEY UPDATE $updateData";
        return $this;
    }

    /**
     * creates the delete statement requires a where clause to be added before it works
     */
    public function delete($table) {
        $this->reset()->sqlData['type'] = "statement";
        $this->sqlData['function'] = "delete";
        $this->sql = "DELETE" .DB_NAME.".".$table;
        return $this;
    }

    /**
     * add a where clause to the statement required in the delete statement
     */
    public function where($columns, $data) {
        if (isset($this->sqlData['where'])) {throw new Exception("cannot add the same clause twice", 5);}
        if (count($columns) !== count($data)) {throw new Exception("columns and data are not equal", 4);}
        if ($this->sqlData['function'] === null) {throw new Exception("no statement currently in existence", 3);}
        if ($this->sqlData['function'] === "create") {throw new Exception("cannot add where clause to a create statement", 6);}

        $whereData = "";
        for ($i = 0; $i < count($data); $i++) {
            $whereData .= $columns[$i]. "=";
            $whereData .= gettype($data[$i]) === "string" ? "'" : "";
            $whereData .= $data[$i];
            $whereData .= gettype($data[$i]) === "string" ? "'" : "";
            $whereData .= count($data) !== ($i + 1) ? " AND " : "";
        }
        $this->sql .= " WHERE " .$whereData;
        $this->sqlData['where'] = 1;
        return $this;
    }

    /**
     * apply a limit to the amount of rows affected if there is a statement in place
     */
    public function limit($range, $start) {
        if ($this->sqlData['function'] === "create") {throw new Exception("cannot add limit clause to a create statement", 6);}
        if (isset($this->sqlData['limit'])) {throw new Exception("cannot add the same clause twice", 5);}
        if ($this->sqlData['function'] === null) {throw new Exception("no statement currently in existence", 3);}
        $limit = $range + $start;
        $start = $start - 1;
        $this->sql .= " LIMIT $limit OFFSET $start";
        $this->sqlData['limit'] = $start - $range;
        return $this;
    }

    /**
     * for if a extra line needs to be added to the statement that is not availble in the function range right now
     */
    public function extraLine($extra) {
        $this->sql .= $extra;
        return $this;
    }

    /**
     * excecute statement whit no return or query statement whit a return
     */
    public function excecuteStatement() {
        global $language;
        // echo $this->sql. '<br>';
        if ($this->sqlData['function'] === "delete" && $this->sqlData['where'] !== 1) {throw new Exception("no where clause added in delete statement", 2);}
        if ($this->sqlData['type'] == "query") {
            $stmt;
            try {
                $stmt = $this->conn->prepare($this->sql.';');
            } catch(PDOException $exception) {
                throw new Exception("Error in excecuting query statement" .$exception->getMessage(), 1);
            }
            $this->reset();
            $stmt->execute();
            return $stmt->fetchAll();
        } else if ($this->sqlData['type'] == "statement") {
            try {
                $this->conn->exec($this->sql.';');
            } catch(PDOException $exception) {
                throw new Exception("Error in excecuting statement" .$exception->getMessage(), 1);
            }
            return $this->reset();
        } else {
            $language->errors['database'] = ['uknownType', $this->sqlData['type']];
            return $this->reset();
        }
    }

    /**
     * gets the edited id
     * do note it wil return the last id not of an statement excecuted before
     */
    public function lastId() {
        return $this->conn->lastInsertId();
    }

    /**
     * destroy the connection and rest everything
     */
    function __destruct() {
        $this->reset();
        $this->conn = null;
        return;
    }
}