# Setup disbursements DB
docker run -d --name disbursement-db -e POSTGRES_USER=sequra -e POSTGRES_PASSWORD=sequrachallenge123 -e POSTGRES_DB=disbursements -p 5432:5432 postgres:10