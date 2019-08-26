module.exports = {
    convertFormToJSON
}

function convertFormToJSON(input) {
    let jsonObject = {};

    for (const [key, value] of input.entries()) {
        jsonObject[key] = value;
    }
    return jsonObject
}

