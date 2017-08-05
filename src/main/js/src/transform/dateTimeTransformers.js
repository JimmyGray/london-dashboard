import moment from 'moment';

export function removeDateFromDateTime(dateTime) {
    const momentDateTime = moment(dateTime);
    return momentDateTime.format('HH:mm:ss');
}