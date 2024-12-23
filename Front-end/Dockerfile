# Use Node.js 20.12.0 as a base image
FROM node:20.12.0-slim

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install --legacy-peer-deps

# Copy all app source files
COPY . .

# Expose the port that Angular's development server uses
EXPOSE 4200

# Start the Angular development server
CMD ["npm", "run", "start"]
