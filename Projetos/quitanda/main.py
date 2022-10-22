from functions import *

db = DatabaseEstoque('dados')
dbLogs = DatabaseLogs('logs')
cliente = Client(db)

menuInicial(db, dbLogs, cliente)