CREATE TABLE Tb_Batch (
        Batch_Id integer PRIMARY KEY, 
        Requestor NVARCHAR(200), 
        Requested_Date_Time DATETIME, 
        Requested_Status VARCHAR(50), 
        Request_For_System VARCHAR(50));


CREATE TABLE Tb_Batch_Item (
        Batch_Id integer,
        Item integer PRIMARY KEY AUTOINCREMENT, 
        Name NVARCHAR(200), 
        Email VARCHAR(50), 
        Init_Password VARCHAR(200), 
        Role VARCHAR(50), 
        Reason_For_Access  NVARCHAR(500),
        FOREIGN KEY (Batch_Id) REFERENCES Tb_Batch(Batch_Id));


INSERT INTO Tb_Batch (Batch_Id, Requestor, Requested_Date_Time, Requested_Status, Request_For_System) 
    VALUES (518, 'bmore', '10/27/2013 12:34:23 PM', 'Queue', 'ClinOp');


INSERT INTO Tb_Batch_Item (Batch_Id, Name, Email, Init_Password, Role, Reason_For_Access)
    VALUES
    (518, 'Susan Smith', 'ssmith@comany1.com', 'susan12%#?', 'SuperUser', 'Because I am "cool", I can do whatever I want.'),
    (518, 'Alex O''Connor', 'alexoconnor@univ1.edu', 'itsuniv1', 'ReadOnly', 'I need to access report for budget < 1M $'),
    (518, 'John J. Peterson', 'john.p@comany2.com', 'J.Pe1234!', 'Auditor', 'Access to 1) all reports; 2) server system logs for "Audit" and [app]_Access_Log'),
    (518, 'Chen, Mei 陈梅', 'chehmei12@123.com', '<:-)>{;=0}', 'ReadOnly', '我负责中国分公司财务');
    

SELECT * FROM Tb_Batch;
SELECT * FROM Tb_Batch_Item;

