# BreachDetection with Apache Drill
Using Apache Drill to calculate log-likelihood Breach Score for Merchants to find source of compromised accounts

This code can be run on a Hadoop cluster, but the test data is small enough to run on your desktop or a VM sandox. If you need to download Drill, get the latest version here: https://drill.apache.org/download/

## Test Data
The file <b>sample.json</b> is synthetic data containing about 1.2M records. The file was created with the program <b>data.py</b> using Poisson random sampling to simulate a customer randomly visiting a sample of merchants. Fraudulent account statuses were randomly selected at a 5% fraud rate. If the data seems reasonable, use the sample provided. But you can make simple changes to <b>data.py</b> to better suit your needs.

## Prepare Drill
This process makes use of a custome function (the logLikelihood ratio). The basic steps for creating a custom function in Drill are here: https://drill.apache.org/docs/tutorial-develop-a-simple-function/. Once you have downloaded the code, perform the following steps:
<BR> 1. cd ./simple-drill-functions-master
<BR> 2. mvn clean package  ## should build 2 jars in ./target
<BR> 3. cp target/*.jar <your drill home>/jars/3rdparty  ## copy the jars to your Drill repository

## Run the code
Examples of queries (including the one to calculate the loglikelihodd) you can run against the data are in <b>drill_queries</b>. You will need to give the full path to the file. It should look something like this: `dfs`.`/Users/xyz/BreachDetection/sample.json`.
<BR> 1. cd to your Drill home directory
<BR> 2. ./bin/drill-embedded  ## start Drill shell
<BR> 3. At Drill prompt, enter queries from <b>drill_queries</b>
