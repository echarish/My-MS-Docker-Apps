/* eslint-disable no-useless-concat */
/* eslint-disable no-shadow */

const express = require('express');

const router = express.Router();
const fetch = require('node-fetch');
// let bookApiService = require('../service/bookApiService');
// let tools = require('../utils/tools');


// let prettyHtml = require('json-pretty-html').default;

const bookApiURL = `http://${ process.env.My_Book_API_MS}/books`;

function getBooks(res, title) {
  console.log(bookApiURL);

  fetch(bookApiURL)
    .then((res) => res.json())
    .then((data) => {
      res.render('index',
        {
          title,
          currentTime: new Date(),
          availableBooks: data,
        });
    })
    .catch(() => {
      res.redirect('/error');
    });
}

/* Post a book */
router.post('/addBook', (req, res) => {
  fetch(bookApiURL, {
    method: 'post',
    body: JSON.stringify(req.body),
    headers: { 'Content-Type': 'application/json' },
  })
    .then((res) => res.json())
    .then(() => {
      getBooks(res, 'The book has been added');
    })
    .catch(() => {
      res.redirect('/error');
    });

  // res.render('index', { title: 'Book has been added ', currentTime: new Date() });
});


/* Get a book */
router.get('/getBook', (req, res) => {
  getBooks(res, 'Here are some books available for you');
});


module.exports = router;
