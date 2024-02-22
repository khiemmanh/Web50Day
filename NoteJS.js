/* 1. JS Toán tử ++ -- tiền tố, hậu tố */

var number = 6;
var output = number++ + --number;
/* number++ => return value is 6, but number = 7
  --number => return 6, number = 6
=> output is 12*/ 

var output01 = ++number * 2 - number-- * 2
// (6+1)*2 + (return 7)*2

console.log('output: ', output);


/* 2. JS Câu lệnh điều kiện If else
    - False value:
        - 0
        - false
        - '' or ""
        - undefined
        - NaN
        - null
*/


/* 3. JS Toán tử so sánh 
    - so sánh == và ===
*/

var a = 1;
var b = '1';

// retrun true. Vì == chỉ so sánh value chứ ko so sánh data type
console.log(a == b); 

// return false. === sẽ só sánh value và data type 
console.log(a === b);






