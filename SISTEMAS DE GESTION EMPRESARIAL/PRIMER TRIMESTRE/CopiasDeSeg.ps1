$DBName = "alejandro"
$DBUser = "Tarde"
$DBPassword = "adasdgsdgds1s"
$BackupDir = "C:\Users\Tarde\Documents\2DAM\SISTEMAS DE GESTION EMPRESARIAL\copias"
$BackupFile = "odoo-backup.dump"


$env:PGPASSWORD = $DBPassword


pg_dump -U $DBUser -F c -b -v -f "$BackupDir\$BackupFile" $DBName