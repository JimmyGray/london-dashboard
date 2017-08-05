export function flattenLists(list) {
    if (list) {
        return list.toList().toJS().reduce((a, b) => a.concat(b));
    }
    return [];
}