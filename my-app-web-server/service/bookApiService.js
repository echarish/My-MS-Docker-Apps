var Request = require("request");

var bookApiURL = "http://localhost:8080/books";

function addBook(book) {

   return Request.post({
        "headers": { "content-type": "application/json" },
        "url": bookApiURL,
        "body": book
    }, (error, response, body) => {
        if (error) {
            return console.dir(error);
        }
        return JSON.parse(body);
    });

}

function getBook() {
   return Request.get(bookApiURL, (error, response, body) => {
        if (error) {
            return console.dir(error);
        }
        return JSON.parse(body);
    });
}


module.exports = {
    addBook,
    getBook
}
