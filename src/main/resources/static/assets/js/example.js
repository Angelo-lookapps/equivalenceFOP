{
    // Styling
    theme: 'striped', // 'striped', 'grid' or 'plain'
    styles: {},
    headerStyles: {},
    bodyStyles: {},
    alternateRowStyles: {},
    columnStyles: {},

    // Properties
    startY: false, // false (indicates margin top value) or a number
    margin: 40, // a number, array or object
    pageBreak: 'auto', // 'auto', 'avoid' or 'always'
    tableWidth: 'auto', // 'auto', 'wrap' or a number, 
    showHeader: 'everyPage' // 'everyPage', 'firstPage', 'never',
    tableLineColor: 200, // number, array (see color section below)
    tableLineWidth: 0,

    // Hooks
    createdHeaderCell: function (cell, data) {},
    createdCell: function (cell, data) {},
    drawHeaderRow: function (row, data) {},
    drawRow: function (row, data) {},
    drawHeaderCell: function (cell, data) {},
    drawCell: function (cell, data) {},
    addPageContent: function (data) {}
 };