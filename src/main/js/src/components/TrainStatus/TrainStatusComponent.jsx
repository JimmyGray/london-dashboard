import React from 'react';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import { removeDateFromDateTime } from '../../transform/dateTimeTransformers';
import classNames from 'classnames';

export default class TrainStatusComponent extends React.Component {

    componentWillReceiveProps() {
    }

    render() {
        return this.renderCard();
    }

    renderCard() {
        return (
            <Card className={'trainStatusCard'}>
                <CardHeader className={'card-header'}>Train Status{this.renderCardHeaderLastUpdated()}</CardHeader>
                <CardText className={'trainTableContainer'}>
                    {this.renderListData()}
                </CardText>
            </Card>
        );
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
                <div className={classNames('row middle-xs', 'trainRow')}>
                    <div className={classNames('statusIndicator', 'col-xs-2')}></div>
                    <div className={classNames('col-xs-5', data.id)}>{data.id}</div>
                    <div className={classNames('col-xs-5')}>{data.lineStatuses[0].statusSeverityDescription}</div>
                </div>);
        })
    }
};