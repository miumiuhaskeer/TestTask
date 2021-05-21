/**
 * Cat name validator
 *
 * @param value name of cat
 * @returns {string|boolean}
 */
function nameValidator(value) {
    let len = value.length;

    if (2 > len || len > 20)
        return 'Name should be between 2 and 20 characters';

    return true;
}

/**
 * Age cat validator
 *
 * @param value age of cat
 * @returns {string|boolean}
 */
function ageValidator(value) {
    let len = value.length;
    let number = parseInt(value);

    if (!value.match(/^\d+$/))
        return 'Age must be contain only numbers';

    if (len > 3 || 0 > number || number > 250)
        return 'Age should be between 0 and 250 years';

    return true;
}