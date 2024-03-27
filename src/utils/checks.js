// 1. Check if string is alphabet
export const isAlphabet = str => /^[a-zA-Z]+$/.test(str);

// 2. Check if string is number
export const isNumber = str => /^\d+$/.test(str);

// 3. Check if string is alphanumeric
export const isAlphanumeric = str => /^[a-zA-Z0-9]+$/.test(str);

// 4. Check if string is alphanumeric with some special symbol
export const isAlphanumericWithSpecial = str => /^[a-zA-Z0-9!@#$%&*()\-+=^]+$/.test(str);

// 5. Check if string is a valid password
export const isValidPassword = str => /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&*()-+=^])(?!.*\s).{8,20}$/.test(str);

// 6. Check If String is a valid email
export const isEmail = str => /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(str);
