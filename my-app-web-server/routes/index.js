const express = require('express');

const router = express.Router();

/* GET home page. */
router.get('/', function(_req, res) {

  const datetime = new Date();

  res.render('index', { title: 'My-APP MicroService test' , currentTime : datetime });
});

module.exports = router;
