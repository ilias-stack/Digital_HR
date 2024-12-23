# Use the official Python 3.9 image as a base image
FROM python:3.9.19-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the requirements file and install dependencies
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copy the rest of the application files
COPY . .

# Run post-install script
RUN python post_install.py

# Expose the port the Flask app runs on
EXPOSE 5000

# Set the command to run the Flask app
CMD ["python", "app.py"]
