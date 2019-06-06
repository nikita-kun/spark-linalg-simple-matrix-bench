pkill java
sleep 1
/sbin/start-master.sh -i localhost
export SPARK_LOCAL_IP=localhost
export SPARK_WORKER_CORES=1
export SPARK_WORKER_INSTANCES=10
export SPARK_WORKER_MEMORY=1G
/sbin/start-slave.sh -h localhost -d /tmp/spark spark://localhost:7077
spark-shell --master spark://localhost:7077 -i /tmp/matrix.scala --executor-memory 1G --driver-memory 4G
