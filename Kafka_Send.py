from kafka import KafkaProducer
import json

# Create a Kafka producer object
producer = KafkaProducer(bootstrap_servers=['localhost:9092'])

# Create a dictionary with the value to send
value = {"Prescence": "Yes"}

# Convert the dictionary to a JSON string
json_value = json.dumps(value).encode('utf-8')

# Send the value to the "my_topic" topic
producer.send('my_topic', value=json_value)

# Wait for all messages to be sent
producer.flush()

# Close the producer
producer.close()
