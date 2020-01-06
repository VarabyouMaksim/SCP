//MOCK service
module.exports = (srv) => {

    srv.on('READ', 'Refrigerators', () => [
        {
            usid: 'U001', name: "Refrig", brand: "Bosch",
            toComment: [
                {cmid: "C001", usid: "U001", text: "Big Refrigerator", toRefrig: {usid: 'U001', name: "Refrig"}}
            ],
            toShelf: [
                { shid: "S001", usid: "U001", material: "Glass", toRefrig: {usid: 'U001', name: "Refrig"} }, { shid: "S002", usid: "U001", material: "Iron", toRefrig: {usid: 'U001', name: "Refrig"}}
            ]
        }
    ]);

    srv.on('READ', 'Shelf', () => [
        { shid: "S001", usid: "U001", material: "Glass", toRefrig: {usid:      'U001', name: "Refrig"} }, { shid: "S002", usid: "U001", material: "Iron", toRefrig: {usid: 'U001', name: "Refrig"}}
    ]);

    srv.on('READ', 'Comment', () => [
        {cmid: "C001", usid: "U001", text: "Big Refrigerator", toRefrig: {usid: 'U001', name: "Refrig"}}
    ]);

   

};
