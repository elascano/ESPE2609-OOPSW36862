const port = 3007;
const express = require("express");
const app = express();
const mongoose = require("mongoose");

mongoose.connect(
    'mongodb://denniscriollo:dennis123@ac-uqxag3u-shard-00-00.dkq3h62.mongodb.net:27017,ac-uqxag3u-shard-00-01.dkq3h62.mongodb.net:27017,ac-uqxag3u-shard-00-02.dkq3h62.mongodb.net:27017/oop?ssl=true&replicaSet=atlas-tdshe8-shard-0&authSource=admin&retryWrites=true&w=majority'
);

const db = mongoose.connection;

db.on('error', (error) => console.error(error));
db.once('open', () => console.log('System connected to MongoDB database'));

app.use(express.json());

const customerRouter = require('./customerRouters');

app.use('/computerstore', customerRouter);

app.listen(port, () => console.log(`Dennis's server is running on port ${port}`));