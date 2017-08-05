import React from 'react';
import {Card, CardActions, CardHeader, CardMedia, CardTitle, CardText} from 'material-ui/Card';
// import styles from './TubeStatusStyles.scss';
import {removeDateFromDateTime} from "../../transform/dateTimeTransformers";
import classNames from 'classnames';

export default class TubeStatusComponent extends React.Component {

    render() {
        <div>
            {
                this.props.data.map((data) => {
                    return (
                        <div className={'tubeStatusContainer'}>
                            <div className={classNames('tubeLineName', data.id)}>{data.id}</div>
                            <div className={'tubeLineStatus'}>{data.lineStatuses[0].statusSeverityDescription}</div>
                        </div>
                    );
                })
            }
        </div>
    }

    renderCardHeaderLastUpdated() {
        if (this.props.data && this.props.data.length > 0) {
            return (
                <span className={'card-header-last-checked'}>
                        last updated: {removeDateFromDateTime(this.props.data[0].lastUpdated)}
                </span>
            );
        }
    }

    renderListData() {
        return this.props.data.map((data) => {
            return (
                <div className={'tubeStatusContainer'}>
                    <div className={classNames('tubeLineName', data.id)}>{data.id}</div>
                    <div className={'tubeLineStatus'}>{data.lineStatuses[0].statusSeverityDescription}</div>
                </div>);
        })
    }
};